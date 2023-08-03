package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.RatingDto;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.service.RecipeService;
import com.lydia.vurrukkulluk.service.UserService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

// POST: In Postman, make a HTTP POST request to upload image to the URL http://localhost:8080/image.
// In order to send the image, inside the HTTP body select form-data, enter image as Key
// change type of Key from text to file
// and select image which you want to upload as Value.

// GET: In Postman, make a HTTP GET request to
// http://localhost:8080/image/{id} You will see the image in the response body downloaded from the database.

@AllArgsConstructor
@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageController {

  @Autowired
  private ImageService imageService;
  @Autowired
  private SecurityUtil securityUtil;

  @Autowired
  private UserService userService;
  @Autowired
  private RecipeService recipeService;
  @Autowired
  private ArticleService articleService;

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> getImage(@PathVariable int id) {

    byte[] image = imageService.getImageById(id);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  }
  @ResponseStatus(value = HttpStatus.OK)
  @PostMapping()
  public ResponseEntity<String> add(@RequestParam("image") MultipartFile file, @RequestParam String type, @RequestParam int id) throws IOException {

    switch (type) {
      case "user" -> {
        if (!securityUtil.isAuthorizedUserOrAdmin(id)) {return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");}
        Image image = new Image(file);
        image = imageService.saveImage(image);
        User user = userService.getUserById(id);
        userService.setImageInUser(user,image);
      }
      case "recipe" -> {
        Recipe recipe = recipeService.getRecipeById(id);
        if (!securityUtil.isAuthorizedUserOrAdmin(recipe.getUser().getId())){return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");}
        Image image = new Image(file);
        image = imageService.saveImage(image);
        recipeService.setImageInRecipe(recipe,image);
      }
      case "article" -> {
        if (!securityUtil.isAuthorizedUserOrAdmin(articleService.getArticleById(id).getUser().getId())) {return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");}
        Image image = new Image(file);
        image = imageService.saveImage(image);
        articleService.setImageInArticle(id,image);

      }
    }

    return ResponseEntity.status(HttpStatus.OK).body("Saved image");
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> put(@RequestParam("image") MultipartFile file, @PathVariable int imageId, @PathVariable String type,@PathVariable int objectId) throws IOException{

    String authorizationMessage = imageAuthorization(type,objectId);
    if (!authorizationMessage.equals("authorized")) {
      if (authorizationMessage.equals("not authorized")){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authorizationMessage);
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authorizationMessage);
    }

    imageService.updateImage(imageId,file);
    return ResponseEntity.status(HttpStatus.OK).body("Updated image with id:" + imageId);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id, @PathVariable String type, @PathVariable int objectId ) {

    String authorizationMessage = imageAuthorization(type,objectId);
    if (!authorizationMessage.equals("authorized")) {
      if (authorizationMessage.equals("not authorized")){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authorizationMessage);
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authorizationMessage);
    }

    imageService.deleteImage(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted image with id: " + id);
  }
  private String imageAuthorization(String type, int objectId){
    switch (type) {
      case "user" -> {
        if (!securityUtil.isAuthorizedUserOrAdmin(objectId)) {
          return "not authorized";
        }
      }
      case "recipe" -> {
        Recipe recipe = recipeService.getRecipeById(objectId);
        if (!securityUtil.isAuthorizedUserOrAdmin(recipe.getUser().getId())) {
          return "not authorized";
        }
      }
      case "article" -> {
        if (!securityUtil.isAdmin()) {
          return "not authorized";
        }
      }
      default -> {
        return "invalid type";
      }
    }
    return "authorized";
    }


}

