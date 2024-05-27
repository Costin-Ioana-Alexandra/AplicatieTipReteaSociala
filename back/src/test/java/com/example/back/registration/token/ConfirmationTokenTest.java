package com.example.back.registration.token;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.back.appuser.AppUser;
import com.example.back.appuser.AppUserRole;

import java.time.LocalDateTime;

public class ConfirmationTokenTest {

    @Test
    void testConstructorAndGetters() {
        // Create a sample user for the token
        AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);

        // Create a sample token
        LocalDateTime now = LocalDateTime.now();
        ConfirmationToken token = new ConfirmationToken("sample_token", now, now.plusHours(24), user);

        // Verify token attributes
        assertEquals("sample_token", token.getToken());
        assertEquals(now, token.getCreatedAt());
        assertEquals(now.plusHours(24), token.getExpiresAt());
        assertEquals(user, token.getAppUser());
    }

    @Test
    void testConfirmation() {
        // Create a sample user for the token
        AppUser user = new AppUser("Jane", "Doe", "jane@example.com", "password", AppUserRole.USER);

        // Create a sample token
        LocalDateTime now = LocalDateTime.now();
        ConfirmationToken token = new ConfirmationToken("sample_token", now, now.plusHours(24), user);

        // Confirm the token
        token.setConfirmedAt(now);

        // Verify token confirmation
        assertEquals(now, token.getConfirmedAt());
    }
}
