package com.example.back.appuser;

import com.example.back.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a class service for managing users.
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

  /**
   * Message for a not found user.
   */
  private static final String USER_NOT_FOUND = "user with %s not found";

  /**
   * Repository for accessing user data.
   */
  private final AppUserRepository appUserRepository;

  /**
   * Encoder for encrypting user passwords.
   */
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * Service for handling confirmation tokens.
   */
  private final ConfirmationTokenService confirmationTokenService;

  /**
   * Loads a user by their email.
   *
   * @param email the email of the user to load
   * @return the user details
   * @throws UsernameNotFoundException if the user is not found
   */
  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
  }

  /**
   * Finds a user by their email.
   *
   * @param email the email of the user
   * @return an Optional containing the user if found, or empty if not found
   */
  public Optional<AppUser> findByEmail(final String email) {
    return appUserRepository.findByEmail(email);
  }

  /**
   * Signs up a new user.
   *
   * @param appUser the user to sign up
   * @return a confirmation token for email verification
   * @throws IllegalStateException if the email is already taken
   */
  public String signUpUser(final AppUser appUser) {
    String email = appUser.getEmail();
    boolean userExist = appUserRepository.findByEmail(email).isPresent();

    if (userExist) {
      throw new IllegalStateException("This email is already taken!");
    }

    String encodedPass = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(encodedPass);

    appUserRepository.save(appUser);

    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15), appUser);

    confirmationTokenService.saveConfirmationToken(confirmationToken);

    return token;
  }

  /**
   * Enables a user's account.
   *
   * @param email the email of the user to enable
   * @return the number of affected rows
   */
  public int enableAppUser(final String email) {
    return appUserRepository.enableAppUser(email);
  }
}
