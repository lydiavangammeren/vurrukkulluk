package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    public IngredientController(){

    }
    
    // post as : {"amount":?,"article":{ "id":?},"recipe":{"id":?}}
    @PostMapping("/add")
    public String add(@RequestBody Ingredient ingredient){
        ingredientService.saveIngredient(ingredient);
        return "saved";
    }

}
