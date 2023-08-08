package com.lydia.vurrukkulluk.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VulgarityFilterTest {

    @Test
    void readBlackList() {
        VulgarityFilter test = new VulgarityFilter();
        assertNotNull(test.getBlackList());
        assertNotEquals(new ArrayList<String>(),test.getBlackList());
        assertTrue(test.getBlackList().size() > 200);

    }

    @Test
    void doFilter(){
        VulgarityFilter test = new VulgarityFilter();
        String filterText = "de poep is smerig";
        String filterText2 = "de poepsaus is smerig";
        assertEquals("de *** is smerig",test.doFilter(filterText));
        assertEquals("de poepsaus is smerig",test.doFilter(filterText2));
    }

}