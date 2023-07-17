package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    ArticleRepository repository;
    @Mock
    Article article;
    @Mock
    Image image;
    ArticleServiceImpl articleService;

    @Test
    void saveArticle() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);

        when(repository.save(article)).thenReturn(article);

        assertEquals(article,articleService.saveArticle(article));
    }

    @Test
    void getAllArticles() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);
        List<Article> allArticles = new ArrayList<>();
        allArticles.add(article);
        allArticles.add(article);
        allArticles.add(article);
        allArticles.add(article);

        when(repository.findAll()).thenReturn(allArticles);

        assertEquals(allArticles,articleService.getAllArticles());


    }

    @Test
    void getArticleByName() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);
        List<Article> allArticleswithName = new ArrayList<>();
        allArticleswithName.add(article);
        when(repository.findByName("articlename")).thenReturn(allArticleswithName);

        assertEquals(allArticleswithName,articleService.getArticleByName("articlename"));
    }

    @Test
    void getArticleById() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);

        when(repository.findById(1)).thenReturn(article);

        assertEquals(article,articleService.getArticleById(1));
    }

    @Test
    void updateArticle() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);
        when(repository.save(article)).thenReturn(article);
        assertEquals(article,articleService.saveArticle(article));
    }

    @Test
    void deleteArticleById() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);

        articleService.deleteArticleById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void setImageInArticle() {
        assertNotNull(repository);
        articleService = new ArticleServiceImpl(repository);
        when(repository.findById(1)).thenReturn(article);

        articleService.setImageInArticle(1,image);

        verify(repository).findById(1);
        verify(article).setImage(image);
        verify(repository).save(article);

    }
}