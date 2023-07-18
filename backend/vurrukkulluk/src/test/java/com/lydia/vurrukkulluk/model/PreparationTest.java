package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PreparationTest {
    @Mock
    Recipe recipe;

    @BeforeEach
    void mockTest(){
        assertNotNull(recipe);
    }

    @Test
    void loadAndRead(){
        Preparation preparation = new Preparation();
        preparation.setId(1);
        preparation.setRecipe(recipe);
        preparation.setStep(2);
        preparation.setInstructions("Instructions");

        assertEquals(1,preparation.getId());
        assertEquals(2,preparation.getStep());
        assertEquals(recipe,preparation.getRecipe());
        assertEquals("Instructions", preparation.getInstructions());

    }

    @Test
    void emptyWhenNotFilled(){
        Preparation preparation = new Preparation();

        assertEquals(0,preparation.getId());
        assertEquals(0,preparation.getStep());
        assertNull(preparation.getRecipe());
        assertNull(preparation.getInstructions());
    }

    @Test
    void allArgsConstructor(){
        Preparation preparation = new Preparation(1,recipe,2,"Instructions");

        assertEquals(1,preparation.getId());
        assertEquals(2,preparation.getStep());
        assertEquals(recipe,preparation.getRecipe());
        assertEquals("Instructions", preparation.getInstructions());
    }
}