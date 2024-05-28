package com.example.back.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Represents the class configuration for setting up web-related configurations.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Configures Cross-Origin Resource Sharing (CORS) settings.
   *
   * @param registry the CORS registry
   */
  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:4200")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*").allowCredentials(true);
  }
}
