// src/app/login/login.component.ts
import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { LoginRequest } from '../models/login-request.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginRequest: LoginRequest = { username: '', password: '' };

  constructor(private authService: AuthService) { }

  login() {
    this.authService.login(this.loginRequest).subscribe(
      response => {
        console.log('Login successful:', response);
        // Handle success, e.g., navigate to another page or store the JWT token
      },
      error => {
        console.error('Login failed:', error);
        // Handle error, e.g., show an error message
      }
    );
  }
}
