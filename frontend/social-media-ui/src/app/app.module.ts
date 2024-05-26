// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './services/auth.service';
import { AppRoutingModule } from './app-routing.module'; // Import the routing module


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, // Add FormsModule to imports array
    HttpClientModule,
    AppRoutingModule // Import the routing module
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
