package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.*;

import java.util.List;

public interface ArticleService {
    public Article saveArticle(Article ingredient);
    public List<Article> getAllArticles();
    public List<Article> getArticleByName(String name);

}
