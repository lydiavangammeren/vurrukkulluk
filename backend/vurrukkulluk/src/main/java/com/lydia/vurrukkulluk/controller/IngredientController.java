package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.IngredientCreateDto;
import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.IngredientService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
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
    @Autowired
    private SecurityUtil securityUtil;
    public IngredientController(){

    }

    @GetMapping()
    public List<IngredientDto> getAll(){
        return ingredientService.getAllIngredients().stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    }
    @PostMapping()
    public String add(@RequestBody IngredientCreateDto ingredientCreateDto){
        if (!securityUtil.isIdOfAuthorizedUser(
                ingredientService.getIngredientById(ingredientCreateDto.getRecipeId()).getRecipe().getUser().getId())){
            return "not authorized";
        }
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.saveIngredient(ingredient);
        return "saved";
    }

    @PutMapping()
    public String update(@RequestBody IngredientCreateDto ingredientCreateDto){
        if (!securityUtil.isIdOfAuthorizedUser(
                ingredientService.getIngredientById(ingredientCreateDto.getRecipeId()).getRecipe().getUser().getId())){
            return "not authorized";
        }
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.saveIngredient(ingredient);
        return "updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        if (!securityUtil.isAuthorizedUserOrAdmin(
                ingredientService.getIngredientById(id).getRecipe().getUser().getId())){
            return "not authorized";
        }
        ingredientService.deleteById(id);
        return "deleted";
    }

    @GetMapping("/{id}")
    public IngredientDto getId(@PathVariable int id){
        return convertIngredientToDto(ingredientService.getIngredientById(id));
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
