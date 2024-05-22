import { Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common'; 


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  template: `
<router-outlet></router-outlet> 
  `,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'SpaceChat';
}
