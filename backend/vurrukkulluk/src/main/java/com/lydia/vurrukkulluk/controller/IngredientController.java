package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IngredientService ingredientService;
    public IngredientController(){

    }

    @PostMapping()
    public String add(@RequestBody IngredientDto ingredientDto){
        ingredientService.saveIngredient(reverseIngredientFromDto(ingredientDto));
        return "saved";
    }

    @GetMapping()
    public List<IngredientDto> getAll(){
        return ingredientService.getAllIngredients().stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Ingredient getId(@PathVariable int id){
        return ingredientService.getIngredientById(id);
    }

    @GetMapping("recipe/{id}")
    public List<IngredientDto> getRecepId(@PathVariable int id){

        return ingredientService.getIngredientsRecipeId(id).stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    }

    public IngredientDto convertIngredientToDto(Ingredient ingredient){
        return modelMapper.map(ingredient,IngredientDto.class);
    }

    public Ingredient reverseIngredientFromDto(IngredientDto ingredientDto){
        return modelMapper.map(ingredientDto,Ingredient.class);
    }


}
