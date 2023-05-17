package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Comment;
import com.lydia.vurrukkulluk.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
  List<Comment> findById(int id);
}
