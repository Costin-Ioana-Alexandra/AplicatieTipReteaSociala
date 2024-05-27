package com.example.back.security.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Represents a class configuration for sending emails.
 */
@Configuration
public class MailConfig {

  /**
   * The port used for SMTP communication.
   */
  private static final int SMTP_PORT = 2525;

  /**
   * Configures and returns a JavaMailSender instance for sending emails.
   *
   * @return A configured JavaMailSender instance.
   */
  @Bean
  public JavaMailSender getJavaMailSender() {

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost("sandbox.smtp.mailtrap.io");
    mailSender.setPort(SMTP_PORT);
    mailSender.setUsername("851798ac859e68");
    mailSender.setPassword("8dca44c26b0fda");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    return mailSender;
  }
}
