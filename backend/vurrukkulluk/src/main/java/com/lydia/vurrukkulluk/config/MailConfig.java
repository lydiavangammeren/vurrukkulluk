package com.lydia.vurrukkulluk.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Component
@Validated
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    @NotNull
    private String host;

    @NotNull
    private int port;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
