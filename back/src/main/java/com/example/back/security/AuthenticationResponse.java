package com.example.back.security;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an authentication response containing a JWT token.
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {

  /**
   * The JWT token generated for successful authentication.
   */
  private final String jwt;
}
