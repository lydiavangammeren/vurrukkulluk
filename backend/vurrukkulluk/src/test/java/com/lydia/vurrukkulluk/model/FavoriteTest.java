package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.Role;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavoriteTest {
    @Mock
    Image image;
    @Mock
    User user;
    @Mock
    Recipe recipe;

    @BeforeEach
    void mocktest(){
        assertNotNull(image);
        assertNotNull(user);
        assertNotNull(recipe);
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