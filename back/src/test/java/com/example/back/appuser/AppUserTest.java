package com.example.back.appuser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class AppUserTest {

    private AppUser appUser;
    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String email = "john.doe@example.com";
    private final String password = "password";
    private final AppUserRole role = AppUserRole.USER;

    @BeforeEach
    void setUp() {
        appUser = new AppUser(firstName, lastName, email, password, role);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(firstName, appUser.getFirstName());
        assertEquals(lastName, appUser.getLastName());
        assertEquals(email, appUser.getUsername()); // getUsername() returns email
        assertEquals(password, appUser.getPassword());
        assertEquals(role, appUser.getAppUserRole());
        assertFalse(appUser.getLocked());
        assertFalse(appUser.isEnabled());
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = appUser.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals(role.name(), authorities.iterator().next().getAuthority());
    }

    @Test
    void testAccountNonExpired() {
        assertTrue(appUser.isAccountNonExpired());
    }

    @Test
    void testAccountNonLocked() {
        assertTrue(appUser.isAccountNonLocked());
        appUser.setLocked(true);
        assertFalse(appUser.isAccountNonLocked());
    }

    @Test
    void testCredentialsNonExpired() {
        assertTrue(appUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertFalse(appUser.isEnabled());
        appUser.setEnabled(true);
        assertTrue(appUser.isEnabled());
    }
}
