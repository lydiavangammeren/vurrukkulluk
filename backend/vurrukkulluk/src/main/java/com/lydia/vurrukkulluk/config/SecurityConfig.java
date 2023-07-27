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
           "/",
            "/static/**",
            "/images/**",
            "/index.html",
            "/manifest.json",
            "api/recipes/**",
            "api/users/**",
            "api/ratings/**",
            "api/image/{id}",
            "api/preparations/**",
            "api/kitchentypes",
            "api/kitchenregions",
            "api/categories",
            "api/ingredients/**",
            "api/favorites/**",
            "api/comments/**",
<<<<<<< HEAD
            "api/calendar",
            "api/articles/**",
            "api/articleunits",
            "api/units"
=======
            "api/calendar/**",
            "api/articles/**"


>>>>>>> 39db2d3175f92fdd602ffb061b0d186c1486bf91
    };
    private static final String[] AUTH_WHITELIST_POST = {
            "api/testdata",
            "api/cart"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().and()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST_ALL)
                .permitAll()
                .requestMatchers(HttpMethod.GET,AUTH_WHITELIST_GET)
                .permitAll()
                .requestMatchers(HttpMethod.POST,AUTH_WHITELIST_POST)
                .permitAll()
                .requestMatchers("/api/auth/**")
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
