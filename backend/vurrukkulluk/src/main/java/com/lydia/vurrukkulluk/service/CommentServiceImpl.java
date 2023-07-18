package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
  @Autowired
  private CommentRepository commentRepository;
  @Override
  public void saveComment(Comment comment) {
    commentRepository.save(comment);
  }

  @Override
  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }

  @Override
  public List<Comment> getAllCommentsOfRecipe(int recipeId) {
    return commentRepository.findAllByRecipeId(recipeId);
  }

  @Override
  public Comment getCommentById(int id) {
    return commentRepository.findById(id);
  }

  @Override
  public void updateComment(Comment comment) {
      commentRepository.save(comment);
  }

  @Override
  public void deleteCommentById(int id) {
    commentRepository.deleteById(id);
  }

}
