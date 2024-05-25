import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { LoginComponent } from './login/login.component';
import { MessagesComponent } from './messages/messages.component';
import { MessageService } from './messages/message.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FileService } from './messages/file.service';
import { SettingsComponent } from './settings/settings.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ResetPasswordComponent,
    MessagesComponent
    SettingsComponent
  ],
  imports: [
    BrowserModule,
    BrowserTransferStateModule,
    TransferHttpCacheModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    MessageService,
    FileService
    provideHttpClient({ useFetch: true }) // enable fetch
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
