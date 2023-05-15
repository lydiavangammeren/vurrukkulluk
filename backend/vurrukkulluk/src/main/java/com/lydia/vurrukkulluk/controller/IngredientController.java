package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/add")
    public String add(@RequestBody Ingredient ingredient){
        ingredientService.saveIngredient(ingredient);
        return "new ingredient added";
    }

    @GetMapping("/getAll")
    public List<Ingredient> getAll(){
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/get/{name}")
    public List<Ingredient> getName(@PathVariable String name){
        return ingredientService.getIngredientsByName(name);
    }

}
