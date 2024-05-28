package com.example.back.security;

import com.example.back.registration.RegistrationRequest;
import com.example.back.registration.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the class controller for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RegistrationService registrationService;

    /**
     * Endpoint for user authentication.
     *
     * @param authenticationRequest Request body containing user credentials
     * @return ResponseEntity containing JWT token if authentication is successful,
     *         otherwise returns ResponseEntity with UNAUTHORIZED status
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        Map<String, String> response = new HashMap<>();

        // Validate input
        if (authenticationRequest.getEmail() == null || authenticationRequest.getPassword() == null) {
            response.put("error", "Email and password must be provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            response.put("token", jwt);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            response.put("error", "Invalid email/password combination");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            response.put("error", "An error occurred during authentication");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint for user registration.
     *
     * @param registrationRequest Request body containing user registration details
     * @return ResponseEntity containing the registration token
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequest registrationRequest) {
        Map<String, String> response = new HashMap<>();

        // Validate input
        if (registrationRequest.getEmail() == null || registrationRequest.getPassword() == null) {
            response.put("error", "Email and password must be provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String token = registrationService.register(registrationRequest);
            response.put("message", "Registration success");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "An error occurred during registration");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
