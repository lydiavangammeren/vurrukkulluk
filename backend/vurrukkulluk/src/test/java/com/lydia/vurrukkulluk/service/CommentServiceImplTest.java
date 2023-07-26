package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    Comment comment;
    @Mock
    CommentRepository repository;

    CommentServiceImpl commentService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        commentService = new CommentServiceImpl(repository);
    }

    @Test
    void saveComment() {
        when(repository.save(comment)).thenReturn(comment);
        assertEquals(comment,commentService.saveComment(comment));
        verify(repository).save(comment);
    }

    @Test
    void getAllComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);

        when(repository.findAll()).thenReturn(comments);
        assertEquals(comments,commentService.getAllComments());
        verify(repository).findAll();

    }

    @Test
    void getAllCommentsOfRecipe() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);

        when(repository.findAllByRecipeId(1)).thenReturn(comments);
        assertEquals(comments,commentService.getAllCommentsOfRecipe(1));
        verify(repository).findAllByRecipeId(1);
    }

    @Test
    void getCommentById() {
        when(repository.findById(1)).thenReturn(comment);
        assertEquals(comment,commentService.getCommentById(1));
        verify(repository).findById(1);
    }

    @Test
    void updateComment() {
        when(repository.save(comment)).thenReturn(comment);
        assertEquals(comment,commentService.updateComment(comment));
        verify(repository).save(comment);
    }

    @Test
    void deleteCommentById() {
        commentService.deleteCommentById(1);
        verify(repository).deleteById(1);
    }
}