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
 * Web security configuration for the application.
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
   * The password encoder for hashing passwords.
   */
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * The JWT request filter for processing JWT authentication requests.
   */
  private final JwtRequestFilter jwtRequestFilter;

  /**
   * Configures the HTTP security filter chain.
   *
   * @param http The HttpSecurity object for configuration.
   * @return A configured SecurityFilterChain object.
   * @throws Exception Exception thrown in case of configuration error.
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
   * Configures the DAO authentication provider.
   *
   * @return A configured DaoAuthenticationProvider object.
   */
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(appUserService);
    return provider;
  }

  /**
   * Configures the authentication manager.
   *
   * @param authenticationConfiguration The authentication configuration.
   * @return A configured AuthenticationManager object.
   * @throws Exception Exception thrown in case of configuration error.
   */
  @Bean
  public AuthenticationManager authenticationManager(
      final AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Returns the user details service.
   *
   * @return The UserDetailsService implementation used for managing user
   *         details.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return appUserService;
  }
}
