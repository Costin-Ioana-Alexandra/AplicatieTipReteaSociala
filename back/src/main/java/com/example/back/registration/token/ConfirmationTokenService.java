package com.example.back.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a class service for managing confirmation tokens.
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  /**
   * Repository for managing confirmation tokens.
   */
  private final ConfirmationTokenRepository confirmationTokenRepository;

  /**
   * Saves a confirmation token.
   *
   * @param token The confirmation token to save.
   */
  public void saveConfirmationToken(final ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  /**
   * Retrieves a confirmation token by its token string.
   *
   * @param token The token string.
   * @return An optional containing the confirmation token if found, otherwise
   *         empty.
   */
  public Optional<ConfirmationToken> getToken(final String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  /**
   * Sets the confirmation time of a confirmation token to the current date and
   * time.
   *
   * @param token The token string.
   * @return The number of tokens updated.
   */
  public int setConfirmedAt(final String token) {
    return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
  }
}
