import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { LoginRequest } from '../models/login-request.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    console.log('LoginComponent initialized');
    this.loginForm = this.fb.group({
      username: ['testuser@test.com', Validators.required], // Default test value
      password: ['parola123!', Validators.required] // Default test value
    });
  }

  login(): void {
    console.log('Login method called');

    if (this.loginForm.valid) {
      const loginRequest: LoginRequest = {
        username: this.loginForm.get('username')!.value,
        password: this.loginForm.get('password')!.value
      };

      this.authService.login(loginRequest).subscribe(
        (response) => {
          console.log('Login successful:', response);
          this.router.navigate(['/messages']); // Redirect to chat
        },
        (error) => {
          // Handle login error
          if (error.status === 401) {
            console.error('Unauthorized: Invalid credentials');
          } else {
            console.error('Login error:', error);
          }

          // Log the actual error response for debugging
          if (error.error) {
            try {
              const parsedError = typeof error.error === 'string' ? JSON.parse(error.error) : error.error;
              console.error('Parsed error:', parsedError);
            } catch (e) {
              console.error('Error response is not valid JSON:', error.error);
            }
          } else {
            console.error('Unknown error occurred');
          }
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }
  goToSign(): void {
    this.router.navigate(['/sign-up']);
  }
  goToSettings(): void {
    this.router.navigate(['/sign-up']);
  }
}
