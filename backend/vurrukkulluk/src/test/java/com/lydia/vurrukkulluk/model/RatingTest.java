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
class RatingTest {
    @Mock
    User user;
    @Mock
    Recipe recipe;

    @BeforeEach
    void mocktest(){
        assertNotNull(user);
        assertNotNull(recipe);
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