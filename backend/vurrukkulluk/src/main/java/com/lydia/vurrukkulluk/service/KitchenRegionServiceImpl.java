package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.repository.KitchenRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
