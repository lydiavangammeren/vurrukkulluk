package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    User user;
    @Mock
    List<User> users;
    @Mock
    UserRepository repository;
    @Mock
    Image image;
    UserServiceImpl userService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        userService = new UserServiceImpl(repository);
    }

    @Test
    void saveUser() {
        when(repository.save(user)).thenReturn(user);
        assertEquals(user,userService.saveUser(user));
    }

    @Test
    void getAllUsers() {
        when(repository.findAll()).thenReturn(users);
        assertEquals(users,userService.getAllUsers());

    }

    @Test
    void getUserByEmail() {
        when(repository.findByEmail("email@a.com")).thenReturn(Optional.ofNullable(user));
        when(repository.findByEmail("000")).thenReturn(Optional.empty());
        assertEquals(user,userService.getUserByEmail("email@a.com"));
        assertThrows(UsernameNotFoundException.class,() -> {userService.getUserByEmail("000");});

    }

    @Test
    void getUserById() {
        when(repository.findById(1)).thenReturn(user);
        assertEquals(user,userService.getUserById(1));

    }

    @Test
    void updateUser() {
        when(repository.save(user)).thenReturn(user);
        assertEquals(user,userService.updateUser(user));
    }

    @Test
    void deleteById() {
        userService.deleteById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void deleteUser() {
        userService.deleteUser(user);
        verify(repository).delete(user);
    }

    @Test
    void setImageInUser() {
        userService.setImageInUser(user,image);
        verify(user).setImage(image);
        verify(repository).save(user);
    }

    @Test
    void setNewPassword() {
        userService.setNewPassword(user,"newPsw");
        verify(user).setPassword("newPsw");
        verify(repository).save(user);
    }
}