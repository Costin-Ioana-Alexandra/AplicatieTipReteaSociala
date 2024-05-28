package com.example.back.appuser;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the data of an user who connects to a social media app.
 */
@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

  /**
   * The identifier for the user.
   */
  @SequenceGenerator(name = "student_sequence",
      sequenceName = "student_sequence",
      allocationSize = 1)
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "student_sequence"
    )
  private Long id;

  /**
   * The first name of the user.
   */
  @Getter
  private String firstName;

  /**
   * The last name of the user.
   */
  @Getter
  private String lastName;

  /**
   * The email of the user.
   */
  private String email;

  /**
   * The password of the user.
   */
  private String password;

  /**
   * The role of the user in the application.
   */
  @Enumerated(EnumType.STRING)
  private AppUserRole appUserRole;

  /**
   * Indicates whether the user's account is locked.
   */
  private Boolean locked = false;

  /**
   * Indicates whether the user's account is enabled.
   */
  private Boolean enabled = false;

  /**
   * Constructs a new AppUser (user) with the specified details.
   *
   * @param firstName   the first name of the user
   * @param lastName    the last name of the user
   * @param email       the email of the user
   * @param password    the password of the user
   * @param appUserRole the role of the user
   */
  public AppUser(
      final String firstName,
      final String lastName,
      final String email,
      final String password,
      final AppUserRole appUserRole) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.appUserRole = appUserRole;
  }

  /**
   * Returns the authorities granted to the user.
   *
   * @return a collection of authorities granted to the user
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
    return Collections.singletonList(authority);
  }

  /**
   * Returns the password used by the user to authenticate.
   *
   * @return the user's password
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Returns the email used by the user to authenticate.
   *
   * @return the user's email
   */
  @Override
  public String getUsername() {
    return email;
  }

  /**
   * Indicates whether the user's account has expired.
   *
   * @return {@code true} if the user's account is non-expired, {@code false}
   *         otherwise
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is locked or unlocked.
   *
   * @return {@code true} if the user's account is non-locked, {@code false}
   *         otherwise
   */
  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  /**
   * Indicates whether the user's credentials (password) have expired.
   *
   * @return {@code true} if the user's credentials are non-expired, {@code false}
   *         otherwise
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled or disabled.
   *
   * @return {@code true} if the user is enabled, {@code false} otherwise
   */
  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
