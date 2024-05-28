package com.example.back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.back.appuser.AppUserService;
import com.example.back.security.JwtRequestFilter;

import lombok.RequiredArgsConstructor;

/**
 * The class configuration for setting up Spring Security.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

  /**
   * The service for managing application users.
   */
  private final AppUserService appUserService;

  /**
   * The password encoder for encoding and decoding passwords.
   */
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * The filter for handling JWT authentication requests.
   */
  private final JwtRequestFilter jwtRequestFilter;

  /**
   * Configures security filter chain.
   *
   * @param http HttpSecurity object to configure security
   * @return The configured SecurityFilterChain
   * @throws Exception If an error occurs during configuration
   */
  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http)
      throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/api/v*/registration/**", "/api/v*/login")
            .permitAll().anyRequest().authenticated())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtRequestFilter,
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /**
   * Configures DaoAuthenticationProvider.
   *
   * @return Configured DaoAuthenticationProvider
   */
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(appUserService);
    return provider;
  }

  /**
   * Configures AuthenticationManager.
   *
   * @param authenticationConfiguration AuthenticationConfiguration object to
   *                                    get AuthenticationManager
   * @return Configured AuthenticationManager
   * @throws Exception If an error occurs during configuration
   */
  @Bean
  public AuthenticationManager authenticationManager(
      final AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Configures UserDetailsService.
   *
   * @return Configured UserDetailsService
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return appUserService;
  }
}
