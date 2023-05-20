package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
  public Comment getCommentById(int id) {
    return commentRepository.findById(id);
  }

  @Override
  public void updateComment(Comment comment) {
      commentRepository.save(comment);
  }

  @Override
  public void deleteComment(Comment comment) {
      commentRepository.delete(comment);
  }
}
