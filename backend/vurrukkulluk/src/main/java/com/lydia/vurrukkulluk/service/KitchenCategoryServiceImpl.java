package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.repository.KitchenCategoryRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitchenCategoryServiceImpl implements KitchenCategoryService{
    @Autowired
    private KitchenCategoryRepository kitchenCategoryRepository;
    @Override
    public Category getCategoryById(int id) {
        return kitchenCategoryRepository.findById(id);
    }
}
