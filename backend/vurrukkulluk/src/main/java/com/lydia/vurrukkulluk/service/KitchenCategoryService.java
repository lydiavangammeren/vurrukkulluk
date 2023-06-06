package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategory;

public interface KitchenCategoryService {

    KitchenCategory getCategoryById(int id);

    KitchenCategory save(KitchenCategory kitchenCategory);


}
