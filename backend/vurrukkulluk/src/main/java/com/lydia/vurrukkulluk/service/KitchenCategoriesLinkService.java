package com.lydia.vurrukkulluk.service;


import com.lydia.vurrukkulluk.model.KitchenCategoriesLink;

import java.util.List;

public interface KitchenCategoriesLinkService {
    void saveKCLink(KitchenCategoriesLink kitchenCategoriesLink);

    List<KitchenCategoriesLink> getKCLinkByRecipeId(int id);
}
