package com.example.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Represents a class configuration for creating a BCrypt password encoder bean.
 */
@Configuration
public class PasswordEncoder {

    /**
     * Creates a BCrypt password encoder bean.
     *
     * @return the BCryptPasswordEncoder bean.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
