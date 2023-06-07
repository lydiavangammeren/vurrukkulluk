package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.IngredientCreateDto;
import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.IngredientService;
import io.swagger.models.auth.In;
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

    @GetMapping()
    public List<IngredientDto> getAll(){
        return ingredientService.getAllIngredients().stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    }
    @PostMapping()
    public String add(@RequestBody IngredientCreateDto ingredientCreateDto){
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.saveIngredient(ingredient);
        return "saved";
    }

    @PutMapping()
    public String update(@RequestBody IngredientCreateDto ingredientCreateDto){
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.saveIngredient(ingredient);
        return "updated";
    }

    @DeleteMapping()
    public String delete(@RequestBody IngredientCreateDto ingredientCreateDto){
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.delete(ingredient);
        return "updated";
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

    public Ingredient reverseIngredientFromCreateDto(IngredientCreateDto ingredientCreateDto){
        return modelMapper.map(ingredientCreateDto,Ingredient.class);
    }

}
