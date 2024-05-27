package com.example.back.registration.token;

import java.time.LocalDateTime;

import com.example.back.appuser.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a confirmation token used for account verification.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

  /**
   * Generates sequence numbers for the confirmation tokens.
   */
  @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence", allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
  private Long id;

  /**
   * The token string.
   */
  @Column(nullable = false)
  private String token;

  /**
   * The date and time when the token was created.
   */
  @Column(nullable = false)
  private LocalDateTime createdAt;

  /**
   * The date and time when the token expires.
   */
  @Column(nullable = false)
  private LocalDateTime expiresAt;

  /**
   * The date and time when the token was confirmed.
   */
  private LocalDateTime confirmedAt;

  /**
   * The user associated with the token.
   */
  @ManyToOne
  @JoinColumn(nullable = false, name = "app_user_id")
  private AppUser appUser;

  /**
   * Constructs a confirmation token with the specified parameters.
   *
   * @param token     The token string.
   * @param createdAt The date and time when the token was created.
   * @param expiresAt The date and time when the token expires.
   * @param appUser   The user associated with the token.
   */
  public ConfirmationToken(final String token, final LocalDateTime createdAt, final LocalDateTime expiresAt,
      final AppUser appUser) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.appUser = appUser;
  }
}
