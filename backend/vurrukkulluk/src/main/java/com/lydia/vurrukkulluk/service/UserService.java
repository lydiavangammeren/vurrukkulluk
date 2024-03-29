package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.User;

import java.util.List;

public interface UserService {

  public User saveUser(User user);
  public List<User> getAllUsers();
  public User getUserByEmail(String email);

  User getUserById(int id);

  public User updateUser(User user);
  public void deleteById(int id);
  public void deleteUser(User user);

  void setImageInUser(User user, Image image);

  void setNewPassword(User user,String password);
}
