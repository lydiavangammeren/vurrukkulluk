package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ArticleUnitTest {

    @Mock
    Article article;
    @Mock
    Unit unit;

    @Test
    void loadAndRead(){
        assertNotNull(article);
        assertNotNull(unit);
        ArticleUnit articleUnit = new ArticleUnit();
        articleUnit.setId(1);
        articleUnit.setAmount(12.34d);
        articleUnit.setUnit(unit);
        articleUnit.setDefUnit(unit);
        articleUnit.setArticle(article);

        assertEquals(1,articleUnit.getId());
        assertEquals(12.34d,articleUnit.getAmount());
        assertEquals(unit,articleUnit.getUnit());
        assertEquals(unit,articleUnit.getDefUnit());
        assertEquals(article,articleUnit.getArticle());
    }

    @Test
    void emptyWhenNotFilled(){
        ArticleUnit articleUnit = new ArticleUnit();
        assertEquals(0,articleUnit.getId());
        assertNull(articleUnit.getUnit());
        assertNull(articleUnit.getDefUnit());
        assertNull(articleUnit.getArticle());
    }

    @Test
    void allArgsConstructor(){
        assertNotNull(article);
        assertNotNull(unit);
        ArticleUnit articleUnit = new ArticleUnit(1,article,unit,12.34d,unit);

        assertEquals(1,articleUnit.getId());
        assertEquals(12.34d,articleUnit.getAmount());
        assertEquals(unit,articleUnit.getUnit());
        assertEquals(unit,articleUnit.getDefUnit());
        assertEquals(article,articleUnit.getArticle());
    }

}