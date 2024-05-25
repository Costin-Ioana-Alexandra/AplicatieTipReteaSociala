package com.example.back.security.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

class MailConfigTest {

    private MailConfig mailConfig;

    @BeforeEach
    void setUp() {
        mailConfig = new MailConfig();
    }

    @Test
    void testGetJavaMailSender() {
        // Call the method to get JavaMailSender
        JavaMailSender javaMailSender = mailConfig.getJavaMailSender();

        // Verify that JavaMailSender is not null
        assertNotNull(javaMailSender);

        // Verify properties of JavaMailSender
        assertTrue(javaMailSender instanceof JavaMailSenderImpl); // Ensure it's JavaMailSenderImpl
        JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) javaMailSender; // Cast to JavaMailSenderImpl

        assertEquals("sandbox.smtp.mailtrap.io", mailSenderImpl.getHost());
        assertEquals(2525, mailSenderImpl.getPort());
        assertEquals("851798ac859e68", mailSenderImpl.getUsername());
        assertEquals("8dca44c26b0fda", mailSenderImpl.getPassword());

        // Verify additional properties
        Properties props = mailSenderImpl.getJavaMailProperties();
        assertEquals("smtp", props.getProperty("mail.transport.protocol"));
        assertEquals("true", props.getProperty("mail.smtp.auth"));
        assertEquals("true", props.getProperty("mail.smtp.starttls.enable"));
        assertEquals("true", props.getProperty("mail.debug"));
    }
}
