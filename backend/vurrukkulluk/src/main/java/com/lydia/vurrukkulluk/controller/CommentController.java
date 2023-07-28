package com.lydia.vurrukkulluk.controller;
import com.lydia.vurrukkulluk.dto.CommentCreateDto;
import com.lydia.vurrukkulluk.dto.CommentDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.CommentService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("api/comments")
@CrossOrigin
public class CommentController {
  @Autowired
  private CommentService commentService;
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SecurityUtil securityUtil;

  @PostMapping()
  public ResponseEntity<String> add(@Valid @RequestBody CommentCreateDto commentCreateDto) {
    if (!securityUtil.isIdOfAuthorizedUser(commentCreateDto.getUserId())){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }

    Comment comment = reverseCommentCreateDto(commentCreateDto);
    commentService.saveComment(comment);

    return ResponseEntity.status(HttpStatus.OK).body("New comment is added");

  }

  @GetMapping()
  public List<CommentDto> getAll() {
    List<Comment> allComments =  commentService.getAllComments();
    List<CommentDto> commentsDto = allComments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
    return commentsDto;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentDto> getId(@PathVariable int id){
    Comment comment = commentService.getCommentById(id);
    if (comment==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.status(HttpStatus.OK).body(convertCommentToDto(comment));
  }

  @GetMapping("/recipe/{id}")
  public List<CommentDto> getCommentsRecipeId(@PathVariable int recipeId) {
    List<Comment> comments = commentService.getAllCommentsOfRecipe(recipeId);

    return  comments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
  }

  @PutMapping()
  public ResponseEntity<String> update(@Valid @RequestBody CommentCreateDto commentCreateDto) {
    if (!securityUtil.isIdOfAuthorizedUser(commentCreateDto.getUserId())){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    Comment comment = reverseCommentCreateDto(commentCreateDto);
    commentService.saveComment(comment);
    return ResponseEntity.status(HttpStatus.OK).body("Comment is updated");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    if (!securityUtil.isAuthorizedUserOrAdmin(commentService.getCommentById(id).getUser().getId())){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    commentService.deleteCommentById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Comment is deleted");
  }


  public CommentDto convertCommentToDto(Comment comment){
    return modelMapper.map(comment,CommentDto.class);
  }

  public Comment reverseCommentCreateDto(CommentCreateDto commentCreateDto) {
    return modelMapper.map(commentCreateDto,Comment.class);
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
