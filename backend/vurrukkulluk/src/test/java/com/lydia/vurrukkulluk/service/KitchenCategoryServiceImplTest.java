package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.repository.KitchenCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KitchenCategoryServiceImplTest {

    @Mock
    KitchenCategory kitchenCategory;
    @Mock
    KitchenCategoryRepository repository;
    KitchenCategoryServiceImpl kitchenCategoryService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        kitchenCategoryService = new KitchenCategoryServiceImpl(repository);
    }

    @Test
    void getCategoryById() {
        when(repository.findById(1)).thenReturn(kitchenCategory);
        assertEquals(kitchenCategory,kitchenCategoryService.getCategoryById(1));
        verify(repository).findById(1);
    }

    @Test
    void save() {
        when(repository.save(kitchenCategory)).thenReturn(kitchenCategory);
        assertEquals(kitchenCategory,kitchenCategoryService.save(kitchenCategory));
        verify(repository).save(kitchenCategory);

    }

    @Test
    void getAllCategories() {
        List<KitchenCategory> categories = new ArrayList<>();
        categories.add(kitchenCategory);
        categories.add(kitchenCategory);
        categories.add(kitchenCategory);

        when(repository.findAll()).thenReturn(categories);
        assertEquals(categories,kitchenCategoryService.getAllCategories());
        verify(repository).findAll();

    }
}