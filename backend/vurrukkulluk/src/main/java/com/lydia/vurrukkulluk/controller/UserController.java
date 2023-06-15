package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.UserCreateDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.FavoriteService;
import com.lydia.vurrukkulluk.service.UserService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.security.auth.login.AccountException;
import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private FavoriteService favoriteService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SecurityUtil securityUtil;

  public UserController() {
  }

  @PostMapping()
  public String add(@RequestBody UserCreateDto userCreateDto) {
    User user = reverseUserToCreateDto(userCreateDto);
    userService.saveUser(user);
    return "New user is added";
  }

  @GetMapping()
  public List<UserDto> getAll() {

    return userService.getAllUsers().stream().map(this::convertUserToDto).collect(Collectors.toList());
  }

  @GetMapping("email/{email}")
  public UserDto getName(@PathVariable String email){
    return convertUserToDto(userService.getUserByEmail(email));
  }


  @GetMapping("/{id}")
  public UserDto getNameDto(@PathVariable int id){
    return  convertUserToDto(userService.getUserById(id));
  }

  @PutMapping("/{id}")
  public String update(@RequestBody UserCreateDto userCreateDto,@PathVariable int id) {
    if (!securityUtil.isAuthorizedUserOrAdmin(id)){
      return "not authorized";
    }
    User user = reverseUserToCreateDto(userCreateDto);
    userService.saveUser(user);
    return "User is updated";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable int id) {

    if (!securityUtil.isAuthorizedUserOrAdmin(id)){
      return "not authorized";
    }
    favoriteService.getFavoritesUserId(id).stream().forEach(favorite -> favoriteService.deleteFavoriteById(favorite.getId()));
    userService.deleteById(id);
    return "User is deleted";
  }


  public UserDto convertUserToDto(User user){
    return modelMapper.map(user,UserDto.class);
  }

  public User reverseUserToCreateDto(UserCreateDto userCreateDto){
    User user = modelMapper.map(userCreateDto,User.class);
    return user;
  }


}
