// src/app/components/sign-up/sign-up.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.signUpForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  signUp(): void {
    console.log("Merge?");

    if (this.signUpForm.valid) {
      const signUpData = this.signUpForm.value;
      this.error = null;
      this.authService.signUp(signUpData).subscribe(
        response => {
          console.log('Sign up successful:', response);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Sign up failed:', error);
          this.error = error.error.message || 'An error occurred during sign up.';
        }
      );
    }
  }

  goToLogin(): void {
    this.router.navigate(['/']);
  }
}
