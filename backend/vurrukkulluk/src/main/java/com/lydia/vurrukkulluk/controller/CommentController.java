package com.lydia.vurrukkulluk.controller;
import com.lydia.vurrukkulluk.dto.CommentCreateDto;
import com.lydia.vurrukkulluk.dto.CommentDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
  @Autowired
  private CommentService commentService;
  @Autowired
  private ModelMapper modelMapper;

  public CommentController() {
  }

  @PostMapping()
  public String add(@RequestBody CommentCreateDto commentCreateDto) {
    Comment comment = reverseCommentCreateDto(commentCreateDto);
    commentService.saveComment(comment);
    return "New comment is added";
  }

  @GetMapping()
  public List<CommentDto> getAll() {
    List<Comment> allComments =  commentService.getAllComments();
    List<CommentDto> commentsDto = allComments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
    return commentsDto;
  }

  @GetMapping("/{id}")
  public CommentDto getId(@PathVariable int id){
    return convertCommentToDto(commentService.getCommentById(id));
  }

  @GetMapping("/recipe/{id}")
  public List<CommentDto> getCommentsRecipeId(@PathVariable int recipeId) {
    List<Comment> comments = commentService.getAllCommentsOfRecipe(recipeId);

    return  comments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
  }

  @PutMapping()
  public String update(@RequestBody CommentCreateDto commentCreateDto) {
    Comment comment = reverseCommentCreateDto(commentCreateDto);
    commentService.saveComment(comment);
    return "Comment is updated";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable int id) {
    commentService.deleteCommentById(id);
    return "Comment is deleted";
  }


  public CommentDto convertCommentToDto(Comment comment){
    return modelMapper.map(comment,CommentDto.class);
  }

  public Comment reverseCommentCreateDto(CommentCreateDto commentCreateDto) {
    return modelMapper.map(commentCreateDto,Comment.class);
  }

}
