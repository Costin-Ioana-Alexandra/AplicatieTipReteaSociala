package com.example.back.registration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @Test
    void testValidEmails() {
        // Valid email addresses based on the current regex
        assertTrue(emailValidator.test("test@example.com"));
        assertTrue(emailValidator.test("user.name+tag@example.com"));
        assertTrue(emailValidator.test("user_name@sub.example.com"));
        assertTrue(emailValidator.test("username123@example.co.uk"));
        assertTrue(emailValidator.test("email@domain.one"));
    }

    @Test
    void testInvalidEmails() {
        // Invalid email addresses based on the current regex
        assertFalse(emailValidator.test("plainaddress")); // Missing '@' and domain
        assertFalse(emailValidator.test("test@")); // Missing domain
        assertFalse(emailValidator.test("test@.com")); // Domain starts with a dot
        assertFalse(emailValidator.test("@example.com")); // Missing local part
        assertFalse(emailValidator.test("test@domain..com")); // Double dots in domain
        assertFalse(emailValidator.test("test@domain.com-")); // Domain ends with a hyphen
        assertFalse(emailValidator.test("test@domain,com")); // Comma in domain
        assertFalse(emailValidator.test("test@domain@domain.com")); // Multiple '@' signs
        assertFalse(emailValidator.test("test@domain..com")); // Multiple dots in domain
        assertFalse(emailValidator.test("test@domain")); // Missing top-level domain
        assertFalse(emailValidator.test("test@.domain.com")); // Dot immediately after '@'
    }
}
