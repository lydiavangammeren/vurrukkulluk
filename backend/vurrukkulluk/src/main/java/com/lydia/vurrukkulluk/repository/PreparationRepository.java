package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreparationRepository extends JpaRepository<Preparation,Integer> {
    List<Preparation> findByRecipeId(int id);
}
