package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.CommentCreateDto;
import com.lydia.vurrukkulluk.dto.CommentDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.CommentService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {
    @Mock
    CommentService commentService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    Comment comment;
    @Mock
    CommentDto commentDto;
    @Mock
    CommentCreateDto commentCreateDto;
    CommentController controller;
    @Mock
    User user;
    private List<Comment> comments;
    private List<CommentDto> commentDtos;

    @BeforeEach
    void makeController(){
        controller = new CommentController(commentService,modelMapper,securityUtil);
    }

    @Test
    void noArgsConstructor(){
        CommentController test = new CommentController();
        assertNotNull(test);
    }

    @Test
    void add() {

        when(commentCreateDto.getUserId()).thenReturn(1);
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(true);
        when(modelMapper.map(commentCreateDto, Comment.class)).thenReturn(comment);
        assertEquals("New comment is added",controller.add(commentCreateDto));
        verify(commentService).saveComment(comment);
    }
    @Test
    void addNotAuthorized() {

        when(commentCreateDto.getUserId()).thenReturn(1);
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(false);

        assertEquals("not authorized",controller.add(commentCreateDto));
        verifyNoInteractions(commentService);
    }

    @Test
    void getAll() {
        comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        commentDtos = new ArrayList<>();
        commentDtos.add(commentDto);
        commentDtos.add(commentDto);
        commentDtos.add(commentDto);

        when(commentService.getAllComments()).thenReturn(comments);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);
        assertEquals(commentDtos,controller.getAll());

    }

    @Test
    void getId() {
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);
        when(commentService.getCommentById(1)).thenReturn(comment);
        assertEquals(commentDto,controller.getId(1));

    }

    @Test
    void getCommentsRecipeId() {
        comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        commentDtos = new ArrayList<>();
        commentDtos.add(commentDto);
        commentDtos.add(commentDto);
        commentDtos.add(commentDto);
        when(commentService.getAllCommentsOfRecipe(1)).thenReturn(comments);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);
        assertEquals(commentDtos,controller.getCommentsRecipeId(1));
    }
    @Test
    void updateWhenAuthorized() {
        when(commentCreateDto.getUserId()).thenReturn(1);
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(true);
        when(modelMapper.map(commentCreateDto, Comment.class)).thenReturn(comment);

        assertEquals("Comment is updated",controller.update(commentCreateDto));
        verify(commentService).saveComment(comment);
    }
    @Test
    void updateWhenNotAuthorized() {
        when(commentCreateDto.getUserId()).thenReturn(1);
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(false);

        assertEquals("not authorized",controller.update(commentCreateDto));
        verifyNoInteractions(commentService);
    }
    @Test
    void deleteWhenAuthorized() {
        when(commentService.getCommentById(1)).thenReturn(comment);
        when(comment.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        assertEquals("Comment is deleted", controller.delete(1));
        verify(commentService).deleteCommentById(1);
    }
    @Test
    void deleteWhenNotAuthorized() {
        when(commentService.getCommentById(1)).thenReturn(comment);
        when(comment.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        assertEquals("not authorized",controller.delete(1));
        verify(commentService).getCommentById(1);
        verifyNoMoreInteractions(commentService);
    }

    @Test
    void convertCommentToDto() {
        controller.setModelMapper(new ModelMapper());
        User user1 = new User();
        user1.setId(1);
        Recipe recipe = new Recipe();
        recipe.setId(1);
        Comment comment1 = new Comment(1,user1,recipe,"comment");
        CommentDto commentDto1 = new CommentDto();
        commentDto1.setId(1);
        UserDto userDto =  new UserDto();
        userDto.setId(1);
        commentDto1.setUser(userDto);
        commentDto1.setCommentText("comment");

        assertEquals(commentDto1,controller.convertCommentToDto(comment1));

    }

    @Test
    void reverseCommentCreateDto() {
        controller.setModelMapper(new ModelMapper());

        CommentCreateDto commentCreateDto1 = new CommentCreateDto(1,"text",1,1);
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setUser(new User());
        comment1.setCommentText("text");
        comment1.getUser().setId(1);
        comment1.setRecipe(new Recipe());
        comment1.getRecipe().setId(1);
        comment1.getRecipe().setTimeAdded(null);

        Comment result = controller.reverseCommentCreateDto(commentCreateDto1);
        result.getRecipe().setTimeAdded(null);
        assertEquals(comment1,result);

    }
}