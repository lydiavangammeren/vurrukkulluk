package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.UserCreateDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.FavoriteService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.service.UserService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.security.auth.login.AccountException;
import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private FavoriteService favoriteService;
  @Autowired
  private ImageService imageService;

  @Setter
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SecurityUtil securityUtil;

  public UserController() {
  }

  @PostMapping()
  public ResponseEntity<String> add(@RequestBody UserCreateDto userCreateDto) {
    User user = reverseUserToCreateDto(userCreateDto);
    user.setImage(new Image());
    user.getImage().setId(1);
    userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.OK).body("New user is added");
  }

  @GetMapping()
  public List<UserDto> getAll() {

    return userService.getAllUsers().stream().map(this::convertUserToDto).collect(Collectors.toList());
  }

  @GetMapping("email/{email}")
  public ResponseEntity<UserDto> getEmail(@PathVariable String email) {
    User user = userService.getUserByEmail(email);
    if (user==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.status(HttpStatus.OK).body(convertUserToDto(user));
  }


  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getId(@PathVariable int id) {
    User user = userService.getUserById(id);
    if (user==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.status(HttpStatus.OK).body(convertUserToDto(user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@RequestBody UserCreateDto userCreateDto, @PathVariable int id) {
    if (!securityUtil.isAuthorizedUserOrAdmin(id)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    User user = reverseUserToCreateDto(userCreateDto);
    userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.OK).body("User is updated");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {

    if (!securityUtil.isAuthorizedUserOrAdmin(id)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    favoriteService.getFavoritesUserId(id).forEach(favorite -> favoriteService.deleteFavoriteById(favorite.getId()));
    Image image = userService.getUserById(id).getImage();
    imageService.deleteImage(image.getId());
    userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body("User is deleted");
  }


  public UserDto convertUserToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

  public User reverseUserToCreateDto(UserCreateDto userCreateDto) {
    User user = modelMapper.map(userCreateDto, User.class);
    return user;
  }


}
