package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenCategoryRepository extends JpaRepository<KitchenCategory,Integer> {
    Category findById(int id);
}
