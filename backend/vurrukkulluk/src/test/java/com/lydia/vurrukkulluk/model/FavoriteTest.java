package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.Role;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteTest {
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
        Favorite favorite = new Favorite();
        favorite.setId(1);
        favorite.setRecipe(recipe);
        favorite.setUser(user);

        assertEquals(1,favorite.getId());
        assertEquals(recipe,favorite.getRecipe());
        assertEquals(user,favorite.getUser());

    }

    @Test
    void emptyWhenNotFilled(){
        Favorite favorite = new Favorite();

        assertEquals(0,favorite.getId());
        assertNull(favorite.getUser());
        assertNull(favorite.getRecipe());
    }

    @Test
    void allArgsConstructor(){
        Favorite favorite = new Favorite(1,user,recipe);

        assertEquals(1,favorite.getId());
        assertEquals(recipe,favorite.getRecipe());
        assertEquals(user,favorite.getUser());
    }
}