package com.example.back.registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * Represents a class controller for handling user registration requests.
 */
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

  /**
   * The service responsible for handling registration logic.
   */
  private final RegistrationService registrationService;

  /**
   * Registers a new user.
   *
   * @param request the registration request containing user details
   * @return a response message from the registration service
   */
  @PostMapping
  public String register(@RequestBody final RegistrationRequest request) {
    return registrationService.register(request);
  }

  /**
   * Confirms a user's registration with a token.
   *
   * @param token the confirmation token
   * @return a response message from the registration service
   */
  @GetMapping(path = "confirm")
  public String confirm(@RequestParam("token") final String token) {
    return registrationService.confirmToken(token);
  }
}
