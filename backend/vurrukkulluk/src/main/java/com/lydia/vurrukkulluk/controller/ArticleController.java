package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    public ArticleController(){

    }

    @PostMapping()
    public String add(@RequestBody Article article){
        System.out.println(article.getPrice());
        articleService.saveArticle(article);
        return "new ingredient added";
    }

    @GetMapping()
    public List<Article> getAll(){
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getId(@PathVariable int id){
        return articleService.getArticleById(id);
    }

    @PatchMapping("/{id}")
    public String update(@RequestBody Article article){
        articleService.updateArticle(article);
        return "article updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        articleService.deleteArticleById(id);
        return "article updated";
    }


}
