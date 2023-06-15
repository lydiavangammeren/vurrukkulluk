package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
  Optional<Image> findByName(String fileName);
  //List<Image> findAllByRecipeId(int recipeId);
}
