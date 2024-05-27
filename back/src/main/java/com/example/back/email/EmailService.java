package com.example.back.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Represents a service class for sending emails.
 */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

  /**
   * Logger for logging email sending errors and information.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
  
  /**
   * JavaMailSender for creating and sending emails.
   */
  private final JavaMailSender mailSender;

  /**
   * Sends an email to the specified recipient.
   *
   * @param receiver the recipient's email address
   * @param email    the content of the email
   */
  @Override
  @Async
  public void send(final String receiver, final String email) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

      helper.setText(email, true);
      helper.setTo(receiver);
      helper.setSubject("Email Verification");
      helper.setFrom("staff.1301B@hotmail.com");

      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("Failed to send email", e);
      throw new IllegalStateException("Failed to send email");
    }
  }
}
