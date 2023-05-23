package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.model.User;

import java.util.List;

public interface CommentService {
  void saveComment(Comment comment);

  List<Comment> getAllComments();

  List<Comment> getAllCommentsOfRecipe(int recipeId);

  Comment getCommentById(int id);

  void updateComment(Comment comment);

  void deleteComment(Comment comment);
}
