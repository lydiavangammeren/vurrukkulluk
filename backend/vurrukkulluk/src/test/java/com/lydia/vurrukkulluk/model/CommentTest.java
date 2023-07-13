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

class CommentTest {

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
        Comment comment = new Comment();
        comment.setId(1);
        comment.setCommentText("commenttext");
        comment.setRecipe(recipe);
        comment.setUser(user);

        assertEquals(1,comment.getId());
        assertEquals("commenttext",comment.getCommentText());
        assertEquals(recipe,comment.getRecipe());
        assertEquals(user,comment.getUser());

    }

    @Test
    void emptyWhenNotFilled(){
        Comment comment = new Comment();

        assertEquals(0,comment.getId());
        assertNull(comment.getUser());
        assertNull(comment.getCommentText());
        assertNull(comment.getRecipe());
    }

    @Test
    void allArgsConstructor(){
        Comment comment = new Comment(1,user,recipe,"commenttext");

        assertEquals(1,comment.getId());
        assertEquals("commenttext",comment.getCommentText());
        assertEquals(recipe,comment.getRecipe());
        assertEquals(user,comment.getUser());
    }
}