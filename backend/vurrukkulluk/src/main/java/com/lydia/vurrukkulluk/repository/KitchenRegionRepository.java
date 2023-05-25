package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.model.KitchenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRegionRepository extends JpaRepository<KitchenRegion,Integer> {
    KitchenRegion findById(int id);
}
