package com.example.back.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Represents the repository interface for managing confirmation tokens.
 */
@Repository
public interface ConfirmationTokenRepository
    extends JpaRepository<ConfirmationToken, Long> {

  /**
   * Retrieves a confirmation token by its token string.
   *
   * @param token The token string.
   * @return An optional containing the confirmation token if found, otherwise
   *         empty.
   */
  Optional<ConfirmationToken> findByToken(String token);

  /**
   * Updates the confirmation time of a confirmation token.
   *
   * @param token       The token string.
   * @param confirmedAt The date and time when the token was confirmed.
   * @return The number of tokens updated.
   */
  @Transactional
  @Modifying
  @Query("UPDATE ConfirmationToken c " + "SET c.confirmedAt = ?2 "
      + "WHERE c.token = ?1")
  int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
