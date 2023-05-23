package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController implements Serializable {

    @Autowired
    private IngredientService ingredientService;
    public IngredientController(){

    }

    // post as : {"amount":?,"article":{ "id":?},"recipe":{"id":?}}
    @PostMapping()
    public String add(@RequestBody Ingredient ingredient){
        ingredientService.saveIngredient(ingredient);
        return "saved";
    }

    @GetMapping()
    public List<Ingredient> getAll(){

        List<Ingredient> res = ingredientService.getAllIngredients();

        return res;
    }

    @GetMapping("/{id}")
    public Ingredient getId(@PathVariable int id){
        return ingredientService.getIngredientById(id);
    }

    @GetMapping("recipe/{id}")
    public Ingredient getRecpId(@PathVariable int id){
        return ingredientService.getIngredientById(id);
    }

}
