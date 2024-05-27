package com.example.back.security;

import com.example.back.registration.RegistrationRequest;
import com.example.back.registration.RegistrationService;
import lombok.RequiredArgsConstructor;
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

/**
 * Represents the class controller for handling authentication-related
 * endpoints.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

  /**
   * The authentication manager for authentication operations.
   */
  private final AuthenticationManager authenticationManager;

  /**
   * The utility class for generating JWT tokens.
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
   * @return ResponseEntity containing JWT token if authentication is successful,
   *         otherwise returns ResponseEntity with UNAUTHORIZED status
   */
  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody final AuthenticationRequest authenticationRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          authenticationRequest.getEmail(), authenticationRequest.getPassword()));

      final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      final String jwt = jwtUtil.generateToken(userDetails.getUsername());

      return ResponseEntity.ok(new AuthenticationResponse(jwt));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password combination");
    }
  }

  /**
   * Endpoint for user registration.
   *
   * @param registrationRequest Request body containing user registration details
   * @return ResponseEntity containing the registration token
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody final RegistrationRequest registrationRequest) {
    String token = registrationService.register(registrationRequest);
    return ResponseEntity.ok("Registration success with token: " + token);
  }
}
