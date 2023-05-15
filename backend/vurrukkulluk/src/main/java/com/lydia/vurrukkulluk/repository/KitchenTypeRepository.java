package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenTypeRepository extends JpaRepository<KitchenType,Integer> {
  List<KitchenType> findByType(String type);
}
