package com.example.back.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an authentication request sent by the user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  /**
   * The email of the user.
   */
  private String email;

  /**
   * The password of the user.
   */
  private String password;
}
