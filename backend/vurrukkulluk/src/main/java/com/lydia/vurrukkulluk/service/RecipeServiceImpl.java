package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.repository.RecipeRepository;
import com.lydia.vurrukkulluk.util.VulgarityFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private VulgarityFilter vulgarityFilter;

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        recipe.setDescription(vulgarityFilter.doFilter(recipe.getDescription()));
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
        return recipeRepository.findById(id).get();
    }

    @Override
    public void setImageInRecipe(Recipe recipe, Image image) {
        recipe.setImage(image);
        saveRecipe(recipe);
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
    public Recipe updateRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }
}
