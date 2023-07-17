package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleTest {

    @Mock
    Image imageMock;

    @Test
    void loadAndRead(){

        assertNotNull(imageMock);
        Article article = new Article();
        article.setId(1);
        article.setCalories(1234);
        article.setDescription("desctiption");
        article.setPrice(123);
        article.setAmount(12);
        article.setAvailable(true);
        article.setName("name");
        article.setImage(imageMock);

        assertEquals("name",article.getName());
        assertEquals(1,article.getId());
        assertEquals("desctiption",article.getDescription());
        assertEquals(123,article.getPrice());
        assertEquals(1234,article.getCalories());
        assertTrue(article.isAvailable());
        assertEquals(12,article.getAmount());
        assertEquals(imageMock,article.getImage());
    }

    @Test
    void allArgsConstructor(){
        Article article1 = new Article(1,"name","discr",1,2,3,true,imageMock);
        assertEquals("name",article1.getName());
    }

    @Test
    void emtyWhenNotFilled(){
        Article article1 = new Article();
        assertNull(article1.getDescription());
        assertNull(article1.getImage());
        assertNull(article1.getName());

    }

}