package com.example.back.registration;

import static org.mockito.Mockito.*;

import com.example.back.appuser.AppUserService;
import com.example.back.email.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    @Mock
    private AppUserService appUserService;

    @Mock
    private EmailSender emailSender;

    @Mock
    private EmailValidator emailValidator; // Add mocked EmailValidator

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("John", "Doe", "john@example.com", "password");

        // Mock emailValidator behavior
        when(emailValidator.test(request.getEmail())).thenReturn(true);

        // Act
        registrationService.register(request);

        // Assert
        verify(appUserService, times(1)).signUpUser(any());
        verify(emailSender, times(1)).send(eq(request.getEmail()), anyString());
    }
}
