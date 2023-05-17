package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.repository.KitchenTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KitchenTypeServiceImpl implements KitchenTypeService {
  @Autowired
  private KitchenTypeRepository kitchenTypeRepository;
  @Override
  public KitchenType saveKitchenType(KitchenType kitchenType) {
    return kitchenTypeRepository.save(kitchenType);
  }

  @Override
  public List<KitchenType> getAllKitchenTypes() {
    return kitchenTypeRepository.findAll();
  }

  @Override
  public List<KitchenType> getKitchenTypeByType(String type) {
    return kitchenTypeRepository.findByType(type);
  }

  @Override
  public void updateKitchenType(KitchenType kitchenType) {
    kitchenTypeRepository.save(kitchenType);
  }

  @Override
  public void deleteById(int id) {
    kitchenTypeRepository.deleteById(id);
  }

  @Override
  public void deleteKitchenType(KitchenType kitchenType) {
    kitchenTypeRepository.delete(kitchenType);
  }
}
