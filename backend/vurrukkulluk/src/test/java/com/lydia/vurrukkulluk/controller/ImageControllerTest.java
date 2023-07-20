package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.service.RecipeService;
import com.lydia.vurrukkulluk.service.UserService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    UserService userService;
    @Mock
    ArticleService articleService;
    @Mock
    Image image;
    @Mock
    User user;
    @Mock
    Article article;
    @Mock
    Recipe recipe;
    ImageController controller;
    @BeforeEach
    void makeController(){
        controller = new ImageController(imageService,securityUtil,
                userService,recipeService,articleService);
    }
    @Test
    void getImage() {
        when(imageService.getImageById(1)).thenReturn(new byte[16]);
        ResponseEntity<byte[]> expected = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(new byte[16]);
        assertEquals(expected,controller.getImage(1));
    }

    @Test
    void addUserImageAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);
        when(imageService.saveImage(new Image(file))).thenReturn(image);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(userService.getUserById(1)).thenReturn(user);
        assertEquals("Saved image",controller.add(file,"user",1));
        verify(userService).getUserById(1);
        verify(userService).setImageInUser(user,image);
    }
    @Test
    void addUserImageNotAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        assertEquals("not authorized",controller.add(file,"user",1));
    }

    @Test
    void addRecipeImageAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);
        when(imageService.saveImage(new Image(file))).thenReturn(image);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);

        assertEquals("Saved image",controller.add(file,"recipe",1));
        verify(recipeService).getRecipeById(1);
        verify(recipeService).setImageInRecipe(recipe,image);
    }
    @Test
    void addRecipeImageNotAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);

        assertEquals("not authorized",controller.add(file,"recipe",1));

    }
    @Test
    void addArticleImageAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);
        when(imageService.saveImage(new Image(file))).thenReturn(image);
        when(securityUtil.isAdmin()).thenReturn(true);

        assertEquals("Saved image",controller.add(file,"article",1));
        verify(articleService).setImageInArticle(1,image);
    }
    @Test
    void addArticleImageNotAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);

        when(securityUtil.isAdmin()).thenReturn(false);

        assertEquals("not authorized",controller.add(file,"article",1));

    }
    @Test
    void putAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);

        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(securityUtil.isAdmin()).thenReturn(true);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);

        assertEquals("Updated image with id:1",controller.put(file,1,"user",1));
        assertEquals("Updated image with id:1",controller.put(file,1,"recipe",1));
        assertEquals("Updated image with id:1",controller.put(file,1,"article",1));
        assertEquals("invalid type",controller.put(file,1,"ad89--==[;dafef",1));
        verify(imageService,times(3)).updateImage(1,file);



    }
    @Test
    void putNotAuthorized() throws IOException {
        MultipartFile file = new MockMultipartFile("file",
                "image.png","jpg",new byte[6]);

        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        when(securityUtil.isAdmin()).thenReturn(false);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);

        assertEquals("not authorized",controller.put(file,1,"user",1));
        assertEquals("not authorized",controller.put(file,1,"recipe",1));
        assertEquals("not authorized",controller.put(file,1,"article",1));
        assertEquals("invalid type",controller.put(file,1,"ad89--==[;dafef",1));
        verifyNoInteractions(imageService);


    }

    @Test
    void deleteAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(securityUtil.isAdmin()).thenReturn(true);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        assertEquals("Deleted image with id: 1",controller.delete(1,"user",1));
        assertEquals("Deleted image with id: 1",controller.delete(1,"recipe",1));
        assertEquals("Deleted image with id: 1",controller.delete(1,"article",1));
        assertEquals("invalid type",controller.delete(1,"ad89--==[;dafef",1));
        verify(imageService,times(3)).deleteImage(1);
    }
    @Test
    void deleteNotAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        when(securityUtil.isAdmin()).thenReturn(false);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        assertEquals("not authorized",controller.delete(1,"user",1));
        assertEquals("not authorized",controller.delete(1,"recipe",1));
        assertEquals("not authorized",controller.delete(1,"article",1));
        assertEquals("invalid type",controller.delete(1,"ad89--==[;dafef",1));
        verifyNoInteractions(imageService);
    }
}