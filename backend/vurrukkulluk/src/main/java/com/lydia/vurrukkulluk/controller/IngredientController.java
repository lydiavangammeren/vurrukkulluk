package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.IngredientCreateDto;
import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.IngredientService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/ingredients")
@CrossOrigin
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
    public ResponseEntity<String> add(@RequestBody IngredientCreateDto ingredientCreateDto){
        if (!securityUtil.isIdOfAuthorizedUser(
                ingredientService.getIngredientById(ingredientCreateDto.getRecipeId()).getRecipe().getUser().getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.saveIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.OK).body("saved");
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody IngredientCreateDto ingredientCreateDto){
        if (!securityUtil.isIdOfAuthorizedUser(
                ingredientService.getIngredientById(ingredientCreateDto.getRecipeId()).getRecipe().getUser().getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        Ingredient ingredient = reverseIngredientFromCreateDto(ingredientCreateDto);
        ingredientService.saveIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.OK).body("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        if (!securityUtil.isAuthorizedUserOrAdmin(
                ingredientService.getIngredientById(id).getRecipe().getUser().getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        ingredientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getId(@PathVariable int id){
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertIngredientToDto(ingredient));
    }

    @GetMapping("recipe/{id}")
    public List<IngredientDto> getRecepId(@PathVariable int id){
        return ingredientService.getIngredientsRecipeId(id).stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    }

    public IngredientDto convertIngredientToDto(Ingredient ingredient){
        return modelMapper.map(ingredient,IngredientDto.class);
    }

    public Ingredient reverseIngredientFromCreateDto(IngredientCreateDto ingredientCreateDto){
        return modelMapper.map(ingredientCreateDto,Ingredient.class);
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
