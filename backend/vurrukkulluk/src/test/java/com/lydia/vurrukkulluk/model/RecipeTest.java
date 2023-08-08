package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeTest {

    @Mock
    User user;
    @Mock
    Image image;
    @Mock
    KitchenRegion kitchenRegion;
    @Mock
    KitchenType kitchenType;

    static DateTimeFormatter formatter;
    @BeforeAll
    static void setup(){
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Test
    void loadAndRead(){
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setUser(user);
        recipe.setImage(image);
        recipe.setSlug("slug");
        recipe.setDescription("description");
        recipe.setKitchenRegion(kitchenRegion);
        recipe.setKitchenType(kitchenType);
        recipe.setPersons(4);
        recipe.setTimeAdded(LocalDateTime.parse("2023-06-07 14:00",formatter));
        recipe.setTitle("title");

        assertEquals(1,recipe.getId());
        assertEquals(4,recipe.getPersons());
        assertEquals("slug",recipe.getSlug());
        assertEquals("description",recipe.getDescription());
        assertEquals("title",recipe.getTitle());
        assertEquals(kitchenRegion,recipe.getKitchenRegion());
        assertEquals(kitchenType,recipe.getKitchenType());
        assertEquals(LocalDateTime.parse("2023-06-07 14:00",formatter),recipe.getTimeAdded());
        assertEquals(user,recipe.getUser());
        assertEquals(image,recipe.getImage());

    }

    @Test
    void emptyWhenNotFilled(){
        Recipe recipe = new Recipe();

        assertEquals(0,recipe.getId());
        assertEquals(0,recipe.getPersons());
        assertEquals(0,recipe.getPrepTime());
        assertNull(recipe.getSlug());
        assertNull(recipe.getDescription());
        assertNull(recipe.getTitle());
        assertNull(recipe.getKitchenRegion());
        assertNull(recipe.getKitchenType());
        assertNull(recipe.getUser());
        assertNull(recipe.getImage());
        assertNotNull(recipe.getTimeAdded());

    }

    @Test
    void allArgsConstructor(){
        Recipe recipe = new Recipe(1,kitchenType,kitchenRegion,user,"title","slug","description"
                ,LocalDateTime.parse("2023-06-07 14:00",formatter),image,4,15);

        assertEquals(1,recipe.getId());
        assertEquals(4,recipe.getPersons());
        assertEquals("slug",recipe.getSlug());
        assertEquals("description",recipe.getDescription());
        assertEquals("title",recipe.getTitle());
        assertEquals(kitchenRegion,recipe.getKitchenRegion());
        assertEquals(kitchenType,recipe.getKitchenType());
        assertEquals(LocalDateTime.parse("2023-06-07 14:00",formatter),recipe.getTimeAdded());
        assertEquals(user,recipe.getUser());
        assertEquals(image,recipe.getImage());
        assertEquals(recipe.getPrepTime(),15);
    }
}