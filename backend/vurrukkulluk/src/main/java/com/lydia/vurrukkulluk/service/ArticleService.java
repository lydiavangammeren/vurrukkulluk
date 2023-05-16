package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.*;

import java.util.List;

public interface ArticleService {
    public Article saveArticle(Article article);
    public List<Article> getAllArticles();
    public List<Article> getArticleByName(String name);

    public Article getArticleById(int id);
    public Article updateArticle(Article article);

    public void deleteArticleById(int id);

}
