package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleUnitTest {

    static Article article;
    static Unit unit;

    @BeforeAll
    static void  setup(){
        unit = new Unit(1,"testunit");
        article = new Article(1,"name","discr",1,2,3,true, new Image());
    }

    @Test
    void loadAndRead(){
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
        ArticleUnit articleUnit = new ArticleUnit(1,article,unit,12.34d,unit);

        assertEquals(1,articleUnit.getId());
        assertEquals(12.34d,articleUnit.getAmount());
        assertEquals(unit,articleUnit.getUnit());
        assertEquals(unit,articleUnit.getDefUnit());
        assertEquals(article,articleUnit.getArticle());
    }

}