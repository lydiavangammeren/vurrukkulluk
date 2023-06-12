package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  Optional<User> findByEmail(String email);

  User findById(int id);
}
