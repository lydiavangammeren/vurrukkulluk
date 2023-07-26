package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.repository.KitchenRegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KitchenRegionServiceImpl implements KitchenRegionService{

    @Autowired
    private KitchenRegionRepository kitchenRegionRepository;

    @Override
    public KitchenRegion saveKitchenRegion(KitchenRegion kitchenRegion) {
        return kitchenRegionRepository.save(kitchenRegion);
    }

    @Override
    public KitchenRegion getById(int id) {
        return kitchenRegionRepository.findById(id);
    }

  @Override
  public List<KitchenRegion> getAllRegions() {
    return kitchenRegionRepository.findAll();
  }
}
