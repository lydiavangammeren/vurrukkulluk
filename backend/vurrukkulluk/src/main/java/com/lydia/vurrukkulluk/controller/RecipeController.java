package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {
  @Autowired
  private RecipeService recipeService;
  @PostMapping("/add")
  public String add(@RequestBody Recipe recipe) {
    recipeService.saveRecipe(recipe);
    return "New recipe is added";
  }

  @DeleteMapping("/delete")
  public String delete(@RequestBody Recipe recipe) {
    recipeService.deleteRecipe(recipe);
    return "Recipe is deleted";
  }

  @GetMapping("/getAll")
  public List<Recipe> get() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("/get/{name}")
  public List<Recipe> getTitle(@PathVariable String title){
    return recipeService.getRecipeByTitle(title);
  }

}
