package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserByEmail(String email) {

    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Override
  public User getUserById(int id) {
    return userRepository.findById(id);
  }

  @Override
  public User updateUser(User user) { return userRepository.save(user); }

  @Override
  public void deleteById(int id) {
    userRepository.deleteById(id);
  }

  @Override
  public void deleteUser(User user) {
      userRepository.delete(user);
  }

  @Override
  public void setImageInUser(User user, Image image) {
    user.setImage(image);
    saveUser(user);
  }

  @Override
  public void setNewPassword(User user, String password) {
    user.setPassword(password);
    userRepository.save(user);
  }
}
