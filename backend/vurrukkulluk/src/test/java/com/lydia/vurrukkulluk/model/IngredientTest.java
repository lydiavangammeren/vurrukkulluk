package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientTest {
    @Mock
    Recipe recipe;
    @Mock
    ArticleUnit articleUnit;
    @Mock
    Article article;

    @BeforeEach
    void mocktest(){
        assertNotNull(articleUnit);
        assertNotNull(recipe);
    }
    @Test
    void loadAndRead(){
        when(articleUnit.getArticle()).thenReturn(article);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setAmount(12.5d);
        ingredient.setArticleUnit(articleUnit);
        ingredient.setRecipe(recipe);

        assertEquals(1,ingredient.getId());
        assertEquals(12.5d,ingredient.getAmount());
        assertEquals(articleUnit,ingredient.getArticleUnit());
        assertEquals(article,ingredient.getArticle());
        assertEquals(recipe,ingredient.getRecipe());

    }

    @Test
    void emptyWhenNotFilled(){
        Ingredient ingredient = new Ingredient();

        assertEquals(0,ingredient.getId());
        assertEquals(0.0d,ingredient.getAmount());
        assertNull(ingredient.getRecipe());
        assertNull(ingredient.getArticle());
        assertNull(ingredient.getArticleUnit());
    }

    @Test
    void allArgsConstructor(){
        when(articleUnit.getArticle()).thenReturn(article);
        Ingredient ingredient = new Ingredient(1,12.5d,articleUnit,recipe);

        assertEquals(1,ingredient.getId());
        assertEquals(12.5d,ingredient.getAmount());
        assertEquals(articleUnit,ingredient.getArticleUnit());
        assertEquals(article,ingredient.getArticle());
        assertEquals(recipe,ingredient.getRecipe());
    }

}