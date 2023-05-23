package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping()
  public String add(@RequestBody User user) {
    userService.saveUser(user);
    return "New user is added";
  }

  @GetMapping()
  public List<User> getAll() {
    return userService.getAllUsers();
  }

  @GetMapping("name/{name}")
  public List<User> getName(@PathVariable String name){
    return userService.getUserByName(name);
  }


  @GetMapping("/{id}")
  public UserDto getNameDto(@PathVariable int id){
    User user = userService.getUserById(id);

    return  convertUserToDto(user);
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


  public UserDto convertUserToDto(User user){
    UserDto userDto = modelMapper.map(user,UserDto.class);
    return userDto;
  }

}
