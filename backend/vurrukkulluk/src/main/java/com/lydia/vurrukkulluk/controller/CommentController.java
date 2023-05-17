package com.lydia.vurrukkulluk.controller;
import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
  @Autowired
  private CommentService commentService;

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

}
