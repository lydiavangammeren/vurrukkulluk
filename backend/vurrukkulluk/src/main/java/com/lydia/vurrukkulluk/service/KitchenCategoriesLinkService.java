package com.lydia.vurrukkulluk.service;


import com.lydia.vurrukkulluk.model.KitchenCategoriesLink;

import java.util.List;

public interface KitchenCategoriesLinkService {
    KitchenCategoriesLink saveKCLink(KitchenCategoriesLink kitchenCategoriesLink);

    List<KitchenCategoriesLink> getKCLinkByRecipeId(int id);

  public void deleteById(int id);

}
