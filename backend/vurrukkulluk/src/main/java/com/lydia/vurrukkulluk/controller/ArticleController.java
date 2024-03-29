package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleCreateDto;
import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.ArticleUnitService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/articles")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ArticleUnitService articleUnitService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping()
    public ResponseEntity<String> add(@Valid @RequestBody ArticleCreateDto articleCreateDto){
        articleCreateDto.setId(0);

        Article article = reverseArticleFromDto(articleCreateDto);
        Article articlefinal = articleService.saveArticle(article);
        HashMap<Integer,Double> units = articleCreateDto.getUnits();

        for (Integer unitId : units.keySet()){
            ArticleUnit articleUnit = new ArticleUnit(0,articlefinal,new Unit(),units.get(unitId),new Unit());
            articleUnit.getUnit().setId(unitId);
            articleUnit.getDefUnit().setId(articleCreateDto.getDefaultUnitId());
            articleUnitService.save(articleUnit);
        }

        return ResponseEntity.status(HttpStatus.OK).body("" + article.getId());
    }

    @GetMapping()
    public List<ArticleDto> getAll(){
        return articleService.getAllArticles().stream().map(this::convertArticleToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getId(@PathVariable int id){
        Article article = articleService.getArticleById(id);
        if (article==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertArticleToDto(article));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody ArticleCreateDto articleDto){
        if (!securityUtil.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }

        Article article = reverseArticleFromDto(articleDto);
        articleService.updateArticle(article);

        return ResponseEntity.status(HttpStatus.OK).body("article updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){

        if (!securityUtil.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
      Image image = articleService.getArticleById(id).getImage();
      imageService.deleteImage(image.getId());
      articleService.deleteArticleById(id);
      return ResponseEntity.status(HttpStatus.OK).body("article deleted");

    }

    public ArticleDto convertArticleToDto(Article article){
        return modelMapper.map(article, ArticleDto.class);
    }
    public Article reverseArticleFromDto(ArticleCreateDto articleCreateDto){
        return modelMapper.map(articleCreateDto, Article.class);
    }
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
