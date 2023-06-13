package com.lydia.vurrukkulluk.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST_ALL = {
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**",
            // CSA Controllers
            "/csa/api/token"
            // Vurrukkulluk



    };
    private static final String[] AUTH_WHITELIST_GET = {
            "/recipes/**",
            "/users/**",
            "/ratings/**",
            "/image/{id}",
            "/preparations/**",
            "/kitchentypes",
            "/ingredients/**",
            "/favorites/**",
            "/comments/**",
            "/calendar",
            "/articles/**"


    };
    private static final String[] AUTH_WHITELIST_POST = {
            "/testdata"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST_ALL)
                .permitAll()
                .requestMatchers(HttpMethod.GET,AUTH_WHITELIST_GET)
                .permitAll()
                .requestMatchers(HttpMethod.POST,AUTH_WHITELIST_POST)
                .permitAll()
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
