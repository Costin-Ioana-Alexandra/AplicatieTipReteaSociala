package com.example.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Represents the main class for the Spring Boot application.
 */
@SpringBootApplication
public final class BackApplication {

  /**
   * Private constructor to prevent class instantiation.
   */
  private BackApplication() {
  }

  /**
   * Represents the main method which serves as the entry point for the Spring
   * Boot application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(final String[] args) {
    SpringApplication.run(BackApplication.class, args);
  }
}
