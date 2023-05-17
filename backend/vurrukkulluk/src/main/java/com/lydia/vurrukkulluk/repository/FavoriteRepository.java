package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
  Favorite findById(int id);
}
