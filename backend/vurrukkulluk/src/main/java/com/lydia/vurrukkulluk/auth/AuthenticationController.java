package com.lydia.vurrukkulluk.auth;

import com.lydia.vurrukkulluk.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
  private final UserServiceImpl userService;
  private final AuthenticationService service;
  private final PasswordEncoder passwordEncoder;
  @Operation(summary = "Register a new user", description = "password should be between 8-32 and contain letter, digit and special character")
  @PostMapping("/register")
  public ResponseEntity<?> register(
    @RequestBody RegisterRequest request
  ) {
    try {

    return ResponseEntity.ok(service.register(request));}
    catch (ResponseStatusException e) {
      return ResponseEntity.badRequest()
        .body(e.getMessage());
    }
  }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authent(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/OTP/request")
    public void OTPrequest(
            @RequestBody AuthenticationRequest request
    ){
        String email = request.getEmail();
        service.requestOTP(email);
    }
    @PostMapping("/OTP/authenticate")
    public ResponseEntity<AuthenticationResponse> otpAuthent(
            @RequestBody AuthenticationRequestOTP request
    ){
      AuthenticationResponse response = service.authenticateOTP(request);

      userService.setNewPassword(request.getEmail(),passwordEncoder.encode(request.getNewPassword()));
        return ResponseEntity.ok(response);
    }
}
