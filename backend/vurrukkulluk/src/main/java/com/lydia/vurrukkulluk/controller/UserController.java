package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping()
  public String add(@RequestBody User user) {
    userService.saveUser(user);
    return "New user is added";
  }

  @GetMapping()
  public List<User> getAll() {
    return userService.getAllUsers();
  }

  @GetMapping("/{name}")
  public List<User> getName(@PathVariable String name){
    return userService.getUserByName(name);
  }

  @PutMapping()
  public String update(@RequestBody User user) {
    userService.updateUser(user);
    return "User is updated";
  }

  @DeleteMapping()
  public String delete(@RequestBody User user) {
    userService.deleteUser(user);
    return "User is deleted";
  }

}
