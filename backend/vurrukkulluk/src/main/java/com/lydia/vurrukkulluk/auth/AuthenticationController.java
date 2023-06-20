package com.lydia.vurrukkulluk.auth;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  //@ApiOperation(value = "Register a new user", notes = "password should be between 8-32 and contain letter, digit and special character")
  @Operation(summary = "Register a new user", description = "password should be between 8-32 and contain letter, digit and special character")
//  @ApiResponse(responseCode = "404", description = "foo")
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
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticateOTP(request));
    }
}
