package com.lydia.vurrukkulluk.auth;

import com.lydia.vurrukkulluk.config.JwtService;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.repository.UserRepository;
import com.lydia.vurrukkulluk.util.MailSendService;
import com.lydia.vurrukkulluk.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final MailSendService mailSendService;

    public AuthenticationResponse register(RegisterRequest request) {

      var user = new User();
      user.setName(request.getName());
      repository.findByEmail(request.getEmail()).ifPresentOrElse((value) -> {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is already in use");
      }, () -> {
        user.setEmail(request.getEmail());
      });



        if(passwordValidation(request.getPassword())) {
          user.setPassword(passwordEncoder.encode(request.getPassword()));
        } else {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "password must be between 8-32 characters and must at least contain" +
              " an uppercase and lowercase letter, a digit and a special character");

        }

        user.setRole(Role.USER);
        repository.save(user);
        Map<String,Object> userIdCLaim = new HashMap<>();
        userIdCLaim.put("userId", user.getId());
        var jwt = jwtService.generateToken(userIdCLaim,user);
        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        Map<String,Object> userIdCLaim = new HashMap<>();
        userIdCLaim.put("userId", user.getId());
        var jwt = jwtService.generateToken(userIdCLaim,user);
        return new AuthenticationResponse(jwt);
    }

    private boolean passwordValidation(String password) {
      if (password.length() >= 8 && (password.length() < 33)) {
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasUppercase = uppercase.matcher(password);
        Matcher hasLowercase = lowercase.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        return hasUppercase.find() && hasLowercase.find() && hasDigit.find() && hasSpecial.find();

      } else {
        return false;
      }

    }

    public void requestOTP(String email){
        var user = repository.findByEmail(email)
                .orElseThrow();
        String OTP = generateOTP();
        user.setOTP(passwordEncoder.encode(OTP));
        user.setOTPExpire(new Date(System.currentTimeMillis() + (1000 * 60 * 5)));
        user.setRole(Role.USER);
        repository.save(user);

        if (!mailSendService.sendOTPMail(OTP,user)){
            System.out.printf("OTP for user %s : %s%n",user.getEmail(),OTP);
        };
    }

    private String generateOTP() {
        Random rand = new Random();
        int upperbound = 100000;
        int randInt = rand.nextInt(upperbound);
        return zeroPadding(String.valueOf(randInt));
    }

    private String zeroPadding(String input){
        int length = "99999".length();
        return String.format("%1$" + length + "s", input).replace(' ', '0');
    }

    public AuthenticationResponse authenticateOTP(AuthenticationRequestOTP request){
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if (new Date(System.currentTimeMillis()).before(user.getOTPExpire())) {
            throw new AuthenticationException("Wrong password") {};
        }

        if (! passwordEncoder.matches(request.getPassword(), user.getOTP())) {
            System.out.println("wrong pass");
            throw new AuthenticationException("Wrong password") {};
        }
        user.setOTP(null);
        user.setOTPExpire(null);
        repository.save(user);
        Map<String,Object> userIdCLaim = new HashMap<>();
        userIdCLaim.put("userId", user.getId());
        var jwt = jwtService.generateToken(userIdCLaim,user);
        return new AuthenticationResponse(jwt);
    }

}
