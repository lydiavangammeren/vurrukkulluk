package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.model.User;

import java.util.List;

public interface CommentService {
  Comment saveComment(Comment comment);

  List<Comment> getAllComments();

  List<Comment> getAllCommentsOfRecipe(int recipeId);

  Comment getCommentById(int id);

  Comment updateComment(Comment comment);

  void deleteCommentById(int id);
}
