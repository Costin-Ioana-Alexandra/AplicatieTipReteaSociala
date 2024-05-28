package com.example.back.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.back.appuser.AppUser;

/**
 * Represents the class implementation of Spring Security UserDetails interface
 * for representing user details.
 */
public class CustomUserDetails implements UserDetails {

  /**
   * The application user associated with the user details.
   */
  private final AppUser appUser;

  /**
   * Constructs CustomUserDetails with the given AppUser.
   *
   * @param appUser The application user associated with these user details.
   */
  public CustomUserDetails(final AppUser appUser) {
    this.appUser = appUser;
  }

  /**
   * Retrieves the authorities granted to the user.
   *
   * @return The authorities granted to the user.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections
        .singleton(new SimpleGrantedAuthority(appUser.getAppUserRole().name()));
  }

  /**
   * Retrieves the password used to authenticate the user.
   *
   * @return The password.
   */
  @Override
  public String getPassword() {
    return appUser.getPassword();
  }

  /**
   * Retrieves the email used to authenticate the user.
   *
   * @return The email.
   */
  @Override
  public String getUsername() {
    return appUser.getEmail();
  }

  /**
   * Indicates whether the user's account has expired.
   *
   * @return true if the user's account is valid, false otherwise.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is locked or unlocked.
   *
   * @return true if the user is not locked, false otherwise.
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Indicates whether the user's credentials (password) has expired.
   *
   * @return true if the user's credentials are valid, false otherwise.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled or disabled.
   *
   * @return true if the user is enabled, false otherwise.
   */
  @Override
  public boolean isEnabled() {
    return appUser.isEnabled();
  }
}
