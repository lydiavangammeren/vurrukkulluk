package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.reposetory.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{
    @Autowired
    private IngredientRepository ingredientRepository;
    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        System.out.println("saving");
        System.out.println(ingredient.getName());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getIngredientsByName(String name) {
        return ingredientRepository.findByName(name);
    }


}
