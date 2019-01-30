import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/appcomponent/app.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserService } from './services/user.service';
import { AlertComponent } from './components/alert/alert.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostquestionComponent } from './components/postquestion/postquestion.component';
import { AuthenticationService } from './services/authentication.service';
import { AlertService } from './services/alert.service';
import { SearchQuestionComponent } from './components/search-question/search-question.component';
import { ShowDiscussionsComponent } from './components/show-discussions/show-discussions.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    AlertComponent,
    LoginComponent,
    DashboardComponent,
    PostquestionComponent,
    SearchQuestionComponent,
    ShowDiscussionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [UserService, AuthenticationService, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }
