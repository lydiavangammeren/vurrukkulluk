package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    public ArticleController(){

    }

    @PostMapping()
    public String add(@RequestBody ArticleDto articleDto){
        Article article = reverseArticleFromDto(articleDto);
        articleService.saveArticle(article);
        return "new ingredient added";
    }

    @GetMapping()
    public List<ArticleDto> getAll(){
        return articleService.getAllArticles().stream().map(this::convertArticleToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ArticleDto getId(@PathVariable int id){
        return convertArticleToDto(articleService.getArticleById(id));
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

    public ArticleDto convertArticleToDto(Article article){
        return modelMapper.map(article, ArticleDto.class);
    }
    public Article reverseArticleFromDto(ArticleDto articleDto){
        return modelMapper.map(articleDto, Article.class);
    }
}
