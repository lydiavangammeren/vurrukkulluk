package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Recipe;

import java.util.List;

public interface RecipeService {

  public Recipe saveRecipe(Recipe recipe);
  public List<Recipe> getAllRecipes();
  public List<Recipe> getRecipeByTitle(String title);
  public void updateRecipe(Recipe recipe);
  public void deleteById(int id);
  public void deleteRecipe(Recipe recipe);

}
