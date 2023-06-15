package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.RatingDto;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import com.lydia.vurrukkulluk.util.UserImageUtil;
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


@RestController
@RequestMapping("/image")
public class ImageController {

  @Autowired
  private ImageService imageService;
  @Autowired
  private SecurityUtil securityUtil;

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> getImage(@PathVariable int id) {
    byte[] image = imageService.getImageById(id);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  }
  @ResponseStatus(value = HttpStatus.OK)
  @PostMapping()
  public int add(@RequestParam("image") MultipartFile file) throws IOException {
    Image image = imageService.saveImage(file);
    return image.getId();
  }

  @PutMapping("/{id}")
  public String put(@RequestParam("image") MultipartFile file, @PathVariable int id) throws IOException{
    Image image = imageService.updateImage(file, id);
    return "Updated image with id:" + id;
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable int id) {
    imageService.deleteImage(id);
    return "Deleted image with id: " + id;
  }

}

