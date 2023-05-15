package com.lydia.vurrukkulluk.reciperepository;

import com.lydia.vurrukkulluk.recipemodel.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
}
