// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
    return this.http.post(`${this.baseUrl}/login`, loginRequest);
  }

  signUp(signUpRequest: SignUpRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/registration`, signUpRequest);
  }
}
