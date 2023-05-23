package com.lydia.vurrukkulluk.controller;
import com.lydia.vurrukkulluk.dto.CommentDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Comment;
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
  public String add(@RequestBody Comment comment) {
    commentService.saveComment(comment);
    return "New comment is added";
  }

  @GetMapping()
  public List<Comment> getAll() {
    return commentService.getAllComments();
  }

  @GetMapping("/{id}")
  public Comment getId(@PathVariable int id){
    return commentService.getCommentById(id);
  }

  @GetMapping("/recipe/{id}")
  public List<CommentDto> getCommentsRecipeId(@PathVariable int recipeId) {
    List<Comment> comments = commentService.getAllCommentsOfRecipe(recipeId);

    return  comments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
  }

  @PutMapping()
  // Postman Body: {
  //        "id": 3,
  //        "recipe":{ "id": 3},
  //        "user": {"id": 4},
  //        "commentText": "Lekker!"
  //    }
  public String update(@RequestBody Comment comment) {
    commentService.updateComment(comment);
    return "Comment is updated";
  }

  @DeleteMapping()
  public String delete(@RequestBody Comment comment) {
    commentService.deleteComment(comment);
    return "Comment is deleted";
  }


  public CommentDto convertCommentToDto(Comment comment){
    CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
    return commentDto;
  }
}
