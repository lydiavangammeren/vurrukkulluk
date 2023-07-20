package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.service.KitchenCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/categories")
@CrossOrigin
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
