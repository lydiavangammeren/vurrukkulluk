package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
  Favorite findById(int id);
  List<Favorite> findByUserId(int id);
}
