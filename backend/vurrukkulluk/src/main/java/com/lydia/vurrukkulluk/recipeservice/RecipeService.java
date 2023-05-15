package com.lydia.vurrukkulluk.recipeservice;

import com.lydia.vurrukkulluk.recipemodel.Recipe;

import java.util.List;

public interface RecipeService {

  public Recipe saveRecipe(Recipe recipe);
  public void deleteById(int id);
  public void deleteRecipe(Recipe recipe);
  public List<Recipe> getAllRecipes();
}
