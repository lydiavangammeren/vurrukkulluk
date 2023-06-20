package com.lydia.vurrukkulluk.auth;

import com.lydia.vurrukkulluk.config.JwtService;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.repository.UserRepository;
import com.lydia.vurrukkulluk.util.MailSendService;
import com.lydia.vurrukkulluk.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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

    public AuthenticationResponse authenticateOTP(AuthenticationRequest request){
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
        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

}
