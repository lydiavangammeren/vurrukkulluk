package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.Role;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeAll;
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
class CommentTest {

    @Mock
    Image image;
    @Mock
    User user;
    @Mock
    Recipe recipe;

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