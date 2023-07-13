package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    static Image image;

    @BeforeAll
    static void setup(){
        Image image = new Image();
        try {
            image.setType("file");
            image.setName("name");
            String imagePath = "src\\main\\resources\\images\\VeganBurger.jpg";
            image.setImageData(UserImageUtil.compressImage(Files.readAllBytes(Paths.get(imagePath))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void loadAndRead(){

        Article article = new Article();
        article.setId(1);
        article.setCalories(1234);
        article.setDescription("desctiption");
        article.setPrice(123);
        article.setAmount(12);
        article.setAvailable(true);
        article.setName("name");
        article.setImage(image);

        assertEquals("name",article.getName());
        assertEquals(1,article.getId());
        assertEquals("desctiption",article.getDescription());
        assertEquals(123,article.getPrice());
        assertEquals(1234,article.getCalories());
        assertTrue(article.isAvailable());
        assertEquals(12,article.getAmount());
        assertEquals(image,article.getImage());
    }

    @Test
    void allArgsConstructor(){
        Article article1 = new Article(1,"name","discr",1,2,3,true,image);
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