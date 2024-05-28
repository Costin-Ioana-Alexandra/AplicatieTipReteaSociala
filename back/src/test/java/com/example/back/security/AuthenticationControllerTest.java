package com.example.back.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import com.example.back.registration.RegistrationRequest;
import com.example.back.registration.RegistrationService;

public class AuthenticationControllerTest {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private RegistrationService registrationService;
    private AuthenticationController controller;

    @BeforeEach
    void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        jwtUtil = mock(JwtUtil.class);
        registrationService = mock(RegistrationService.class);
        controller = new AuthenticationController(authenticationManager, jwtUtil, registrationService);
    }

    @Test
    void testCreateAuthenticationToken_SuccessfulAuthentication() {
        // Create a sample user details
        UserDetails userDetails = User.withUsername("test@example.com").password("password").roles("USER").build();

        // Mock authentication manager to return the user details upon successful authentication
        when(authenticationManager.authenticate(any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(userDetails, "password"));

        // Mock JWT token generation
        when(jwtUtil.generateToken("test@example.com")).thenReturn("sample_token");

        // Create authentication request
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "password");

        // Call the endpoint
        ResponseEntity<Map<String, String>> response = controller.createAuthenticationToken(authenticationRequest);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().containsKey("token"));
        assertEquals("sample_token", response.getBody().get("token"));
    }

    @Test
    void testCreateAuthenticationToken_InvalidCredentials() {
        // Mock authentication manager to throw BadCredentialsException for invalid credentials
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Create authentication request with invalid credentials
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "invalid_password");

        // Call the endpoint
        ResponseEntity<Map<String, String>> response = controller.createAuthenticationToken(authenticationRequest);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
        assertEquals("Invalid email/password combination", response.getBody().get("error"));
    }

    @Test
    void testRegister() {
        // Mock registration service to return a sample token
        when(registrationService.register(any())).thenReturn("sample_token");

        // Create registration request
        RegistrationRequest registrationRequest = new RegistrationRequest("John", "Doe", "john@example.com", "password");

        // Call the endpoint
        ResponseEntity<Map<String, String>> response = controller.register(registrationRequest);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().containsKey("token"));
        assertEquals("sample_token", response.getBody().get("token"));
    }
}