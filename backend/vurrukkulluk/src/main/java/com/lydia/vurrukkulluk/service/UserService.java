package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;

import java.util.List;

public interface UserService {

  public User saveUser(User user);
  public List<User> getAllUsers();
  public List<User> getUserByName(String name);
  public void updateUser(User user);
  public void deleteById(int id);
  public void deleteUser(User user);
}
