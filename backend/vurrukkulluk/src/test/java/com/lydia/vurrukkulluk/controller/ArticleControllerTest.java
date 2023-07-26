package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.ArticleUnitService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
    ImageService imageService;
    @Mock
    Image image;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    private ModelMapper modelMapper;
    ArticleController articleController;

    @BeforeEach
    void makeController(){
        articleController = new ArticleController(articleService,
                imageService,modelMapper,securityUtil);
    }

    @Test
    void add() {
        when(modelMapper.map(articleDto,Article.class)).thenReturn(article);
        assertEquals("new ingredient added",articleController.add(articleDto));
        verify(articleService).saveArticle(article);
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
        assertEquals(articleDto,articleController.getId(1));
    }

    @Test
    void updateWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);

        assertEquals("article updated",articleController.update(article));
        verify(articleService).updateArticle(article);
    }
    @Test
    void updateWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals("not authorized",articleController.update(article));
        verifyNoInteractions(articleService);
    }

    @Test
    void deleteWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        when(articleService.getArticleById(1)).thenReturn(article);
        when(article.getImage()).thenReturn(image);

        assertEquals("article updated",articleController.delete(1));
        verify(imageService).deleteImage(image.getId());
        verify(articleService).deleteArticleById(1);
    }
    @Test
    void deleteWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);

        assertEquals("not authorized",articleController.delete(1));
        verifyNoInteractions(imageService);
        verifyNoInteractions(articleService);
    }

    @Test
    void convertArticleToDto() {
        articleController.setModelMapper(new ModelMapper());
        when(image.getId()).thenReturn(1);
        Article article = new Article(1,"name","des",
                2,3,4,true,image);
        ArticleDto articleDto = new ArticleDto(1,"name","des",2,3,4,1);

        assertEquals(articleDto,articleController.convertArticleToDto(article));
    }

    @Test
    void reverseArticleFromDto() {
        articleController.setModelMapper(new ModelMapper());
        Image image1 = new Image();
        image1.setId(1);
        Article article = new Article(1,"name","des",
                2,3,4,false,image1);
        ArticleDto articleDto = new ArticleDto(1,"name","des",2,3,4,1);

        assertEquals(article,articleController.reverseArticleFromDto(articleDto));

    }
}