package com.example.back.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.registration.RegistrationRequest;
import com.example.back.registration.RegistrationService;

import lombok.RequiredArgsConstructor;

/**
 * Represents the class controller for handling authentication-related
 * endpoints.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

  /**
   * The authentication manager for handling user authentication.
   */
  private final AuthenticationManager authenticationManager;

  /**
   * The JWT utility for generating and validating JWT tokens.
   */
  private final JwtUtil jwtUtil;

  /**
   * The service for handling user registration.
   */
  private final RegistrationService registrationService;

  /**
   * Endpoint for user authentication.
   *
   * @param authenticationRequest Request body containing user credentials
   * @return ResponseEntity containing JWT token if authentication is
   *         successful, otherwise returns ResponseEntity with UNAUTHORIZED
   *         status
   */
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> createAuthenticationToken(
      @RequestBody final AuthenticationRequest authenticationRequest) {
    Map<String, String> response = new HashMap<>();

    if (authenticationRequest.getEmail() == null
        || authenticationRequest.getPassword() == null) {
      response.put("error", "Email and password must be provided");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(),
              authenticationRequest.getPassword()));

      final UserDetails userDetails = (UserDetails) authentication
          .getPrincipal();
      final String jwt = jwtUtil.generateToken(userDetails.getUsername());

      response.put("token", jwt);
      return ResponseEntity.ok(response);
    } catch (BadCredentialsException e) {
      response.put("error", "Invalid email/password combination");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    } catch (Exception e) {
      response.put("error", "An error occurred during authentication");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(response);
    }
  }

  /**
   * Endpoint for user registration.
   *
   * @param registrationRequest Request body containing user registration
   *                            details
   * @return ResponseEntity containing the registration token
   */
  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(
      @RequestBody final RegistrationRequest registrationRequest) {
    Map<String, String> response = new HashMap<>();

    if (registrationRequest.getEmail() == null
        || registrationRequest.getPassword() == null) {
      response.put("error", "Email and password must be provided");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    try {
      String token = registrationService.register(registrationRequest);
      response.put("message", "Registration success");
      response.put("token", token);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.put("error", "An error occurred during registration");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(response);
    }
  }
}
