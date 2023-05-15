package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/add")
  public String add(@RequestBody User user) {
    userService.saveUser(user);
    return "New user is added";
  }

  @GetMapping("/getAll")
  public List<User> getAll() {
    return userService.getAllUsers();
  }

  @GetMapping("/get/{name}")
  public List<User> getName(@PathVariable String name){
    return userService.getUserByName(name);
  }

  @PutMapping("/update")
  public String update(@RequestBody User user) {
    userService.updateUser(user);
    return "User is updated";
  }

  @DeleteMapping("/delete")
  public String delete(@RequestBody User user) {
    userService.deleteUser(user);
    return "User is deleted";
  }

}
