package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.KitchenCategoriesLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenCategoriesLinkRepository extends JpaRepository<KitchenCategoriesLink,Integer> {
    List<KitchenCategoriesLink> findByRecipeId(int id);
}
