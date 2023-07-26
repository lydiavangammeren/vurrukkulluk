package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class KitchenRegionTest {


    @Test
    void loadAndRead(){
        KitchenRegion kitchenRegion = new KitchenRegion();
        kitchenRegion.setId(1);
        kitchenRegion.setName("name");

        assertEquals(1,kitchenRegion.getId());
        assertEquals("name",kitchenRegion.getName());

    }

    @Test
    void emptyWhenNotFilled(){
        KitchenRegion kitchenRegion = new KitchenRegion();

        assertEquals(0,kitchenRegion.getId());
        assertNull(kitchenRegion.getName());
    }

    @Test
    void allArgsConstructor(){
        KitchenRegion kitchenRegion = new KitchenRegion(1,"name");

        assertEquals(1,kitchenRegion.getId());
        assertEquals("name",kitchenRegion.getName());

    }
}