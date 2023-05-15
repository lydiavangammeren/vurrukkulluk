package com.lydia.vurrukkulluk.recipeservice;

import com.lydia.vurrukkulluk.recipemodel.Recipe;
import com.lydia.vurrukkulluk.reciperepository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImplementation implements RecipeService {
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
  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }
}
