package com.lydia.vurrukkulluk.auth;

import com.lydia.vurrukkulluk.config.JwtService;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.repository.UserRepository;
import com.lydia.vurrukkulluk.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        if(passwordValidation(request.getPassword())) {
          user.setPassword(passwordEncoder.encode(request.getPassword()));
        } else {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "password must be between 8-32 characters and must at least contain" +
              " a letter, a digit and a special character");

        }

        user.setRole(Role.USER);
        repository.save(user);
        var jwt = jwtService.generateToken(user);
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
        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

    private boolean passwordValidation(String password) {
      if (password.length() >= 8 && (password.length() < 33)) {
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        return hasLetter.find() && hasDigit.find() && hasSpecial.find();

      } else {
        return false;
      }

    }
}
