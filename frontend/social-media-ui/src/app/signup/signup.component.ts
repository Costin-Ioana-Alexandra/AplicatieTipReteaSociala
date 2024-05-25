// src/app/components/sign-up/sign-up.component.ts
import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { SignUpRequest } from '../models/signup-request.model';

@Component({
  selector: 'app-sign-up',
  templateUrl: 'signup.component.html',
  styleUrls: ['signup.component.css']
})
export class SignUpComponent {
  signUpRequest: SignUpRequest = { lastname: '', firstname: '', email: '', password: '' };
  error: string | null = null; // Variable to store error messages, initially set to null

  constructor(private authService: AuthService) { }

  signUp() {
    // Reset error message
    this.error = null;

    // Call AuthService to sign up user
    this.authService.signUp(this.signUpRequest).subscribe(
      response => {
        console.log('Sign up successful:', response);
        // Handle success, e.g., navigate to the login page
      },
      error => {
        console.error('Sign up failed:', error);
        // Set error message
        this.error = error.error.message || 'An error occurred during sign up.';
      }
    );
  }
}
