package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleCreateDto;
import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.ArticleUnitService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {

    @Mock
    ArticleService articleService;
    @Mock
    Article article;
    @Mock
    ArticleDto articleDto;
    @Mock
    ArticleCreateDto articleCreateDto;
    @Mock
    ImageService imageService;
    @Mock
    Image image;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ArticleUnitService articleUnitService;

    private Validator validator;
    ArticleController articleController;

    @BeforeEach
    void makeController(){
        articleController = new ArticleController(articleService,
                imageService,articleUnitService,modelMapper,securityUtil);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void add() {
        when(modelMapper.map(articleCreateDto,Article.class)).thenReturn(article);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("0"),articleController.add(articleCreateDto));
        verify(articleService).saveArticle(article);
    }

    @Test
    void assertionTest() {
        ArticleDto articleDto1 = new ArticleDto();
        Set<ConstraintViolation<ArticleDto>> violations =  validator.validate(articleDto1);
        assertFalse(violations.isEmpty());
        ArticleDto articleDto2 =  new ArticleDto(1,"","des",2,3,4,false,1,5);
        assertFalse( validator.validate(articleDto2).isEmpty());
        ArticleDto articleDto3 =  new ArticleDto(1,"name","",2,3,4,false,1,5);
        assertFalse( validator.validate(articleDto3).isEmpty());
        ArticleDto articleDto4 =  new ArticleDto(1,"name","des",-5,3,4,false,1,5);
        assertFalse( validator.validate(articleDto4).isEmpty());
        ArticleDto articleDto6 =  new ArticleDto(1,"name","des",2,3,0,false,1,5);
        assertFalse( validator.validate(articleDto6).isEmpty());
        ArticleDto articleDto7 =  new ArticleDto(1,"name","des",2,3,4,false,1,0);
        assertFalse( validator.validate(articleDto7).isEmpty());
        ArticleDto articleDto5 =  new ArticleDto(1,"name","des",2,3,4,false,1,5);
        assertTrue( validator.validate(articleDto5).isEmpty());
    }

    @Test
    void getAll() {
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        articles.add(article);
        articles.add(article);

        List<ArticleDto> result = new ArrayList<>();
        result.add(articleDto);
        result.add(articleDto);
        result.add(articleDto);

        when(articleService.getAllArticles()).thenReturn(articles);
        when(modelMapper.map(article,ArticleDto.class)).thenReturn(articleDto);
        assertEquals(result,articleController.getAll());
    }

    @Test
    void getId() {
        when(modelMapper.map(article,ArticleDto.class)).thenReturn(articleDto);
        when(articleService.getArticleById(1)).thenReturn(article);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(articleDto),articleController.getId(1));
    }

    @Test
    void updateWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        when(modelMapper.map(articleCreateDto,Article.class)).thenReturn(article);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("article updated"),articleController.update(articleCreateDto));
        verify(articleService).updateArticle(article);
    }
    @Test
    void updateWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),articleController.update(articleCreateDto));
        verifyNoInteractions(articleService);
    }

    @Test
    void deleteWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        when(articleService.getArticleById(1)).thenReturn(article);
        when(article.getImage()).thenReturn(image);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body("article deleted"),articleController.delete(1));
        verify(imageService).deleteImage(image.getId());
        verify(articleService).deleteArticleById(1);
    }
    @Test
    void deleteWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);

        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),articleController.delete(1));
        verifyNoInteractions(imageService);
        verifyNoInteractions(articleService);
    }

    @Test
    void convertArticleToDto() {
        articleController.setModelMapper(new ModelMapper());
        when(image.getId()).thenReturn(1);
        Article article = new Article(1,"name","des",
                2,3,4,true,image,new User());
        article.getUser().setId(5);
        ArticleDto articleDto = new ArticleDto(1,"name","des",2,3,4,true,1,5);

        assertEquals(articleDto,articleController.convertArticleToDto(article));
    }

    @Test
    void reverseArticleFromDto() {
        articleController.setModelMapper(new ModelMapper());
        Image image1 = new Image();
        image1.setId(1);
        Article article = new Article(1,"name","des",
                2,3,4,false,image1,new User());
        article.getUser().setId(5);
        ArticleCreateDto articleDto = new ArticleCreateDto(1,"name","des",2,3,4,false,1,5,1,new HashMap<>());

        assertEquals(article,articleController.reverseArticleFromDto(articleDto));

    }
}