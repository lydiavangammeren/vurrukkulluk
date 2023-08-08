package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.repository.CommentRepository;
import com.lydia.vurrukkulluk.util.VulgarityFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private VulgarityFilter vulgarityFilter;
  @Override
  public Comment saveComment(Comment comment) {
    comment.setCommentText(vulgarityFilter.doFilter(comment.getCommentText()));
    return commentRepository.save(comment);
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
  public Comment updateComment(Comment comment) { return commentRepository.save(comment); }

  @Override
  public void deleteCommentById(int id) {
    commentRepository.deleteById(id);
  }

}
