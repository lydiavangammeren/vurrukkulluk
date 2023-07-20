package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.service.KitchenCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KitchenCategoryControllerTest {

    @Mock
    KitchenCategoryService kitchenCategoryService;
    @Mock
    List<KitchenCategory> kitchenCategories;
    KitchenCategoryController controller;

    @BeforeEach
    void makeController(){
        controller = new KitchenCategoryController(kitchenCategoryService);
    }

    @Test
    void noArgsConstructor(){
        KitchenCategoryController test = new KitchenCategoryController();
        assertNotNull(test);
    }

    @Test
    void getAll() {
        when(kitchenCategoryService.getAllCategories()).thenReturn(kitchenCategories);
        assertEquals(kitchenCategories,controller.getAll());
    }
}