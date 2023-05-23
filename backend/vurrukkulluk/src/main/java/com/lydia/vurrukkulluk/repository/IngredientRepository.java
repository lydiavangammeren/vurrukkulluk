package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
    Ingredient findById(int id);

    List<Ingredient> findByRecipeId(int id);
}
