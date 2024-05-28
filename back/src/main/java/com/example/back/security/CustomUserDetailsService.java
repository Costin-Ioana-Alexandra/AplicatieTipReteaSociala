package com.example.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.back.appuser.AppUserService;

/**
 * Represents the class implementation of Spring Security UserDetailsService
 * interface for loading user-specific data.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  /**
   * Service for interacting with application users.
   */
  @Autowired
  private AppUserService appUserService;

  /**
   * Locates the user based on the username. In this case, it uses the email to
   * find the user.
   *
   * @param email the email address identifying the user whose data is required.
   * @return A UserDetails object that Spring Security uses for authentication
   *         and validation.
   * @throws UsernameNotFoundException if the user could not be found.
   */
  @Override
  public UserDetails loadUserByUsername(final String email)
      throws UsernameNotFoundException {
    return appUserService.findByEmail(email).map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
