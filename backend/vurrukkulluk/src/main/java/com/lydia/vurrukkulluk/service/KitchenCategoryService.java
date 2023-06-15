package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategory;

import java.util.List;

public interface KitchenCategoryService {

    KitchenCategory getCategoryById(int id);

    KitchenCategory save(KitchenCategory kitchenCategory);

    public List<KitchenCategory> getAllCategories();

}
