package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenType;

import java.util.List;

public interface KitchenTypeService {
  public KitchenType saveKitchenType(KitchenType kitchenType);
  public List<KitchenType> getAllKitchenTypes();
  public List<KitchenType> getKitchenTypeByType(String type);
  public void updateKitchenType(KitchenType kitchenType);
  public void deleteById(int id);
  public void deleteKitchenType(KitchenType kitchenType);
}
