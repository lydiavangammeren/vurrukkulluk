package com.lydia.vurrukkulluk.auth;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

  private final AuthenticationService service;

  @ApiOperation(value = "Register a new user", notes = "password should be between 8-32 and contain letter, digit and special character")
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
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
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
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticateOTP(request));
    }
}
