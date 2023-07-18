package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.repository.KitchenTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class KitchenTypeServiceImpl implements KitchenTypeService {
  @Autowired
  private KitchenTypeRepository kitchenTypeRepository;
  @Override
  public KitchenType saveKitchenType(KitchenType kitchenType) {
    return kitchenTypeRepository.save(kitchenType);
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
  public List<KitchenType> getAll() {
    return kitchenTypeRepository.findAll();
  }

  @Override
  public KitchenType getById(int id) {
    return kitchenTypeRepository.findById(id);
  }

}
