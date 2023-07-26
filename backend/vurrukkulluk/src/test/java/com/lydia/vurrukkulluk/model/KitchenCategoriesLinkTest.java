package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class KitchenCategoriesLinkTest {
    @Mock
    Recipe recipe;
    @Mock
    KitchenCategory kitchenCategory;

    @BeforeEach
    void mocktest(){
        assertNotNull(recipe);
    }

    @Test
    void loadAndRead(){
        KitchenCategoriesLink link = new KitchenCategoriesLink();
        link.setId(1);
        link.setRecipe(recipe);
        link.setKitchenCategory(kitchenCategory);

        assertEquals(1,link.getId());
        assertEquals(recipe,link.getRecipe());
        assertEquals(kitchenCategory,link.getKitchenCategory());

    }

    @Test
    void emptyWhenNotFilled(){
        KitchenCategoriesLink link = new KitchenCategoriesLink();

        assertEquals(0,link.getId());
        assertNull(link.getRecipe());
        assertNull(link.getKitchenCategory());
    }

    @Test
    void allArgsConstructor(){
        KitchenCategoriesLink link = new KitchenCategoriesLink(1,kitchenCategory,recipe);

        assertEquals(1,link.getId());
        assertEquals(recipe,link.getRecipe());
        assertEquals(kitchenCategory,link.getKitchenCategory());
    }
}