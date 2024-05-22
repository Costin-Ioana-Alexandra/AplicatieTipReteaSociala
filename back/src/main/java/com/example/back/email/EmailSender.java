package com.example.back.email;

/**
 * Represents the interface for sending emails.
 */
public interface EmailSender {
	
    /**
     * Sends an email to the specified recipient.
     *
     * @param to    the recipient's email address
     * @param email the content of the email
     */
    void send(String to, String email);
}
