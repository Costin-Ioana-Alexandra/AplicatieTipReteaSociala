package com.example.back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Represents a class configuration for sending emails.
 */
@Configuration
public class MailConfig {

    /**
     * Configures and returns a JavaMailSender instance for sending emails.
     *
     * @return A configured JavaMailSender instance.
     */
    @Bean
    public JavaMailSender getJavaMailSender() {
    	
    	/**
    	 * The instance of JavaMailSenderImpl.
    	 */
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        /**
         * The SMTP server host.
         */
        mailSender.setHost("sandbox.smtp.mailtrap.io");
        
        /**
         * The SMTP server port.
         */
        mailSender.setPort(2525);

        /**
         * The username for SMTP authentication.
         */
        mailSender.setUsername("851798ac859e68");
        
        /**
         * The password for SMTP authentication.
         */
        mailSender.setPassword("8dca44c26b0fda");

        /**
         * Additional properties configuration.
         */
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
