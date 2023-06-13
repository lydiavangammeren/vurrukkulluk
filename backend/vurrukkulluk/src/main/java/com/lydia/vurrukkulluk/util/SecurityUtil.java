package com.lydia.vurrukkulluk.util;

import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityUtil {

    @Autowired
    private UserService userService;

    public User getAuthorizedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByEmail(email);
    }

    public boolean isIdOfAuthorizedUser(int id){
        User AuthUser = getAuthorizedUser();
        return AuthUser.getId() == id;
    }

    public boolean isAdmin() {
        User AuthUser = getAuthorizedUser();
        return AuthUser.getRole() == Role.ADMIN;
    }
}
