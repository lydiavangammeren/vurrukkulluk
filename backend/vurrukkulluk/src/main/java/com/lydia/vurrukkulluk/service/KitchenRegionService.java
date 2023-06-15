package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.model.KitchenType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface KitchenRegionService {
  public KitchenRegion saveKitchenRegion(KitchenRegion kitchenRegion);

  public KitchenRegion getById(int id);

  public List<KitchenRegion> getAllRegions();

}
