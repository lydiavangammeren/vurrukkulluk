package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
 @Autowired
  private RecipeRepository recipeRepository;

  @Override
  public Recipe saveRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  @Override
  public void deleteById(int id) {
    recipeRepository.deleteById(id);
  }

  @Override
  public void deleteRecipe(Recipe recipe) {
    recipeRepository.delete(recipe);
  }

    @Override
    public Recipe getRecipeById(int id) {
        return recipeRepository.getReferenceById(id);
    }

    @Override
  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  @Override
  public List<Recipe> getRecipeByTitle(String title) {
    return recipeRepository.findByTitle(title);
  }

@Override
public Recipe getRecipeBySlug(String slug) {
    return recipeRepository.findBySlug(slug);
}

    @Override
  public void updateRecipe(Recipe recipe) {
    recipeRepository.save(recipe);
  }
}
