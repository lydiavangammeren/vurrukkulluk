package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KitchenCategoryTest {
    @Test
    void loadAndRead(){
        KitchenCategory kitchenCategory = new KitchenCategory();
        kitchenCategory.setId(1);
        kitchenCategory.setName("name");

        assertEquals(1,kitchenCategory.getId());
        assertEquals("name",kitchenCategory.getName());
    }

    @Test
    void emptyWhenNotFilled(){
        KitchenCategory kitchenCategory = new KitchenCategory();

        assertEquals(0,kitchenCategory.getId());
        assertNull(kitchenCategory.getName());
    }

    @Test
    void allArgsConstructor(){
        KitchenCategory kitchenCategory = new KitchenCategory(1,"name");

        assertEquals(1,kitchenCategory.getId());
        assertEquals("name",kitchenCategory.getName());

    }
}