package com.example.back.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Defines a data transfer object for user registration requests.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	
    /**
     * The first name of the user.
     */
    private final String firstName;
    
    /**
     * The last name of the user.
     */
    private final String lastName;
    
    /**
     * The email of the user.
     */
    private final String email;
    
    /**
     * The password of the user.
     */
    private final String password;
}
