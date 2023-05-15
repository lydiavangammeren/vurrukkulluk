package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository ingredientRepository;
    @Override
    public Article saveArticle(Article article) {
        System.out.println("saving");
        System.out.println(article.getName());
        return ingredientRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return ingredientRepository.findAll();
    }

    @Override
    public List<Article> getArticleByName(String name) {
        return ingredientRepository.findByName(name);
    }


}
