package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Mock
    Image image;
    @Mock
    Date date;

    @Test
    void loadAndRead(){
        User user = new User();
        user.setId(1);
        user.setImage(image);
        user.setName("name");
        user.setEmail("a@a.com");
        user.setOTP("00000");
        user.setOTPExpire(date);
        user.setRole(Role.USER);
        user.setPassword("password");

        assertEquals(1,user.getId());
        assertEquals(image,user.getImage());
        assertEquals("name",user.getName());
        assertEquals("a@a.com",user.getEmail());
        assertEquals("00000",user.getOTP());
        assertEquals(date,user.getOTPExpire());
        assertEquals(Role.USER,user.getRole());
        assertEquals("password",user.getPassword());
    }

    @Test
    void emptyWhenNotFilled(){
        User user = new User();

        assertEquals(0,user.getId());
        assertNull(user.getImage());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getOTP());
        assertNull(user.getOTPExpire());
        assertNull(user.getRole());
        assertNull(user.getPassword());
    }
    @Test
   void getAuthorities(){
        User user = new User();
        user.setRole(Role.USER);

        System.out.println(user.getAuthorities());
        assertEquals(List.of(new SimpleGrantedAuthority(Role.USER.name())),user.getAuthorities());
    }
    @Test
    void getUsername(){
        User user = new User();
        user.setEmail("email@a.com");
        assertEquals("email@a.com",user.getUsername());
    }

    @Test
    void allArgsConstructor(){
        User user = new User(1,"name","password","00000",date,"a@a.com",image,Role.USER);

        assertEquals(1,user.getId());
        assertEquals(image,user.getImage());
        assertEquals("name",user.getName());
        assertEquals("a@a.com",user.getEmail());
        assertEquals("00000",user.getOTP());
        assertEquals(date,user.getOTPExpire());
        assertEquals(Role.USER,user.getRole());
        assertEquals("password",user.getPassword());
    }

    @Test
    void isAccountNonExpired() {
        User user = new User();
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        User user = new User();
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        User user = new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        User user = new User();
        assertTrue(user.isEnabled());
    }
}