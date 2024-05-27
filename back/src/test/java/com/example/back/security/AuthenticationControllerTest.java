package com.example.back.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.back.registration.RegistrationRequest;
import com.example.back.registration.RegistrationService;

public class AuthenticationControllerTest {

    @Test
    void testCreateAuthenticationToken_SuccessfulAuthentication() {
        // Mock dependencies
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        JwtUtil jwtUtil = mock(JwtUtil.class);

        // Create a sample user details
        UserDetails userDetails = User.withUsername("test@example.com").password("password").roles("USER").build();

        // Mock authentication manager to return the user details upon successful authentication
        when(authenticationManager.authenticate(any()))
            .thenReturn(new UsernamePasswordAuthenticationToken(userDetails, "password"));
        
        // Mock JWT token generation
        when(jwtUtil.generateToken("test@example.com")).thenReturn("sample_token");

        // Create instance of AuthenticationController
        AuthenticationController controller = new AuthenticationController(authenticationManager, jwtUtil, null);

        // Create authentication request
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "password");

        // Call the endpoint
        ResponseEntity<?> response = controller.createAuthenticationToken(authenticationRequest);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof AuthenticationResponse);
        assertEquals("sample_token", ((AuthenticationResponse) response.getBody()).getJwt());
    }

    @Test
    void testCreateAuthenticationToken_InvalidCredentials() {
        // Mock dependencies
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        JwtUtil jwtUtil = mock(JwtUtil.class);

        // Mock authentication manager to throw BadCredentialsException for invalid credentials
        when(authenticationManager.authenticate(any()))
            .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Create instance of AuthenticationController
        AuthenticationController controller = new AuthenticationController(authenticationManager, jwtUtil, null);

        // Create authentication request with invalid credentials
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "invalid_password");

        // Call the endpoint
        ResponseEntity<?> response = controller.createAuthenticationToken(authenticationRequest);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email/password combination", response.getBody());
    }

    @Test
    void testRegister() {
        // Mock dependencies
        RegistrationService registrationService = mock(RegistrationService.class);

        // Mock registration service to return a sample token
        when(registrationService.register(any())).thenReturn("sample_token");

        // Create instance of AuthenticationController
        AuthenticationController controller = new AuthenticationController(null, null, registrationService);

        // Create registration request
        RegistrationRequest registrationRequest = new RegistrationRequest("John", "Doe", "john@example.com", "password");

        // Call the endpoint
        ResponseEntity<?> response = controller.register(registrationRequest);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("sample_token", response.getBody());
    }
}
