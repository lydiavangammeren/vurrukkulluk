package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Recipe;

import java.util.List;

public interface RecipeService {

  Recipe saveRecipe(Recipe recipe);
  List<Recipe> getAllRecipes();
  List<Recipe> getRecipeByTitle(String title);

  Recipe getRecipeBySlug(String slug);
  void updateRecipe(Recipe recipe);
  void deleteById(int id);
  void deleteRecipe(Recipe recipe);

}
