package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.Role;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    static Image image;
    static User user;
    static Recipe recipe;
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
        user = new User(1,"name","password","1234",new Date(),"123@email.com",image, Role.USER);
        recipe = new Recipe(1,new KitchenType(),new KitchenRegion(),user,"title","slug",
                "description", LocalDateTime.now(),image,4);
    }

    @Test
    void loadAndRead(){
        Rating rating = new Rating();
        rating.setId(1);
        rating.setRating(4);
        rating.setRecipe(recipe);
        rating.setUser(user);

        assertEquals(1,rating.getId());
        assertEquals(4,rating.getRating());
        assertEquals(recipe,rating.getRecipe());
        assertEquals(user,rating.getUser());

    }

    @Test
    void emptyWhenNotFilled(){
        Rating rating = new Rating();

        assertEquals(0,rating.getId());
        assertEquals(0,rating.getRating());
        assertNull(rating.getUser());
        assertNull(rating.getRecipe());
    }

    @Test
    void allArgsConstructor(){
        Rating rating = new Rating(1,4,user,recipe);

        assertEquals(1,rating.getId());
        assertEquals(4,rating.getRating());
        assertEquals(recipe,rating.getRecipe());
        assertEquals(user,rating.getUser());
    }
}