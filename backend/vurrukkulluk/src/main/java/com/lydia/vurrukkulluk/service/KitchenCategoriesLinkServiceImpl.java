package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategoriesLink;
import com.lydia.vurrukkulluk.repository.KitchenCategoriesLinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KitchenCategoriesLinkServiceImpl implements KitchenCategoriesLinkService {
    @Autowired
    private KitchenCategoriesLinkRepository kitchenCategoriesLinkRepository;
    @Override
    public KitchenCategoriesLink saveKCLink(KitchenCategoriesLink kitchenCategoriesLink) {
        return kitchenCategoriesLinkRepository.save(kitchenCategoriesLink);
    }

    @Override
    public List<KitchenCategoriesLink> getKCLinkByRecipeId(int id) {
        return kitchenCategoriesLinkRepository.findByRecipeId(id);
    }

  @Override
  public void deleteById(int id) {
    kitchenCategoriesLinkRepository.deleteById(id);
  }
}
