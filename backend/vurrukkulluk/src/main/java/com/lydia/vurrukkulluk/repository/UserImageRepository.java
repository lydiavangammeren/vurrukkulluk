package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
  Optional<UserImage> findByName(String fileName);
}
