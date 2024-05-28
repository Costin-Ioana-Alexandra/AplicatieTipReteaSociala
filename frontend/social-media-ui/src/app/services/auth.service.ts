// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../models/login-request.model';
import { SignUpRequest } from '../models/signup-request.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }

  login(loginRequest: LoginRequest): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const body = {
        email: loginRequest.username,
        password: loginRequest.password
    };
    return this.http.post(`${this.baseUrl}/login`, body, { headers });
  }

  signUp(signUpRequest: SignUpRequest): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const body = {
        firstName: signUpRequest.firstname,
        lastName: signUpRequest.lastname,
        email: signUpRequest.email,
        password: signUpRequest.password
    };

    return this.http.post(`${this.baseUrl}/registration`, body, { headers });
  }
}
