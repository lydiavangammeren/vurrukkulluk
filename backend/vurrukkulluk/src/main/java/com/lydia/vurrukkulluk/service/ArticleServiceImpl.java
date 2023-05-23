package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article saveArticle(Article article) {
        System.out.println("saving");
        System.out.println(article.getName());
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getArticleByName(String name) {
        return articleRepository.findByName(name);
    }

    @Override
    public Article getArticleById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticleById(int id) {
        articleRepository.deleteById(id);
    }


}