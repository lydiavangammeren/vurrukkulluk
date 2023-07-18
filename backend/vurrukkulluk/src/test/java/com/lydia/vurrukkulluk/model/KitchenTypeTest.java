package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KitchenTypeTest {
    @Test
    void loadAndRead(){
        KitchenType kitchenType = new KitchenType();
        kitchenType.setId(1);
        kitchenType.setName("name");

        assertEquals(1,kitchenType.getId());
        assertEquals("name",kitchenType.getName());

    }

    @Test
    void emptyWhenNotFilled(){
        KitchenType kitchenType = new KitchenType();

        assertEquals(0,kitchenType.getId());
        assertNull(kitchenType.getName());
    }

    @Test
    void allArgsConstructor(){
        KitchenType kitchenType = new KitchenType(1,"name");

        assertEquals(1,kitchenType.getId());
        assertEquals("name",kitchenType.getName());

    }
}