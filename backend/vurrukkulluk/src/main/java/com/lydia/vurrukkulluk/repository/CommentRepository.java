package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
  Comment findById(int id);
}
