package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitTest {
    @Test
    void loadAndRead(){
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("test");

        assertEquals(1,unit.getId());
        assertEquals("test",unit.getName());

    }

    @Test
    void emptyWhenNotFilled(){
        Unit unit = new Unit();
        assertEquals(0,unit.getId());
        assertNull(unit.getName());
    }

    @Test
    void allArgsConstructor(){

        Unit unit = new Unit(1,"test");

        assertEquals(1,unit.getId());
        assertEquals("test",unit.getName());
    }

}