package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/articles")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

  @Autowired
  private ImageService imageService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecurityUtil securityUtil;

    public ArticleController(){

    }

    @PostMapping()
    public String add(@RequestBody ArticleDto articleDto){
        Article article = reverseArticleFromDto(articleDto);
        articleService.saveArticle(article);
//        return "new ingredient added";
        return "" + article.getId();
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
        if (!securityUtil.isAdmin()) {
            return "not authorized";
        }
        articleService.updateArticle(article);
        return "article updated";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){

        if (!securityUtil.isAdmin()){
            return "not authorized";
        }
      Image image = articleService.getArticleById(id).getImage();
      imageService.deleteImage(image.getId());
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
