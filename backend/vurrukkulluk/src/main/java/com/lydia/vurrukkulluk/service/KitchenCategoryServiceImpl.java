package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.repository.KitchenCategoryRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitchenCategoryServiceImpl implements KitchenCategoryService{
    @Autowired
    private KitchenCategoryRepository kitchenCategoryRepository;
    @Override
    public KitchenCategory getCategoryById(int id) {
        return kitchenCategoryRepository.findById(id);
    }

    @Override
    public KitchenCategory save(KitchenCategory kitchenCategory) {
        return kitchenCategoryRepository.save(kitchenCategory);

    }
}
