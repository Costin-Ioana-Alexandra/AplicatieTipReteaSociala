package com.example.back.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Represents a repository interface for managing {@link AppUser} entities.
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

  /**
   * Finds an {@link AppUser} by their email.
   *
   * @param email the email of the user
   * @return an {@link Optional} containing the user if found, or empty if not
   *         found
   */
  Optional<AppUser> findByEmail(String email);

  /**
   * Enables an {@link AppUser} by setting their enabled status to true.
   *
   * @param email the email of the user to be enabled
   * @return the number of rows affected
   */
  @Transactional
  @Modifying
  @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1")
  int enableAppUser(String email);

}
