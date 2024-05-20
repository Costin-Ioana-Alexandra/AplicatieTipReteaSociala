import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { MessagesComponent } from './messages/messages.component';
import { SettingsComponent } from './settings/settings.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'sign-up', pathMatch: 'full', component: SignupComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'messages', component: MessagesComponent },
  { path: 'settings', component: SettingsComponent },
  { path: '', redirectTo: '', pathMatch: 'full' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
