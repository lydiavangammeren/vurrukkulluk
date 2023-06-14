package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.KitchenCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class KitchenCategoryController {

  @Autowired
  private KitchenCategoryService kitchenCategoryService;

  public KitchenCategoryController(){

  }

  @GetMapping()
  public List<KitchenCategory> getAll(){
    return kitchenCategoryService.getAllCategories();
  }
}
