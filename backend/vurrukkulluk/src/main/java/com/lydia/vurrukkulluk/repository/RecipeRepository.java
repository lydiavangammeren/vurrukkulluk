package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
  List<Recipe> findByTitle(String title);
}
