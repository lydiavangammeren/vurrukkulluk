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

    @PostMapping("/add")
    public String add(@RequestBody Article article){
        System.out.println(article.getPrice());
        articleService.saveArticle(article);
        return "new ingredient added";
    }

    @GetMapping("/getAll")
    public List<Article> getAll(){
        return articleService.getAllArticles();
    }

    @GetMapping("/get/{name}")
    public List<Article> getName(@PathVariable String name){
        return articleService.getArticleByName(name);
    }

}
