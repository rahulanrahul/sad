import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostquestionComponent } from './components/postquestion/postquestion.component';
import { SearchQuestionComponent } from './components/search-question/search-question.component';
import { MyprofileComponent } from './components/myprofile/myprofile.component';
import { MyanswersComponent } from './components/myanswers/myanswers.component';
import { MyquestionsComponent } from './components/myquestions/myquestions.component';
import { EditprofileComponent } from './components/editprofile/editprofile.component';
import { EditquestionComponent } from './components/editquestion/editquestion.component';
import { EditanswersComponent } from './components/editanswers/editanswers.component';
const routes: Routes = [
  { path: '', component: LoginComponent, },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'myprofile', component: MyprofileComponent },
  { path: 'myanswers', component: MyanswersComponent },
  { path: 'myquestions', component: MyquestionsComponent },
  { path: 'postquestion', component: PostquestionComponent },
  { path: 'search-question', component: SearchQuestionComponent },
  { path: 'editprofile', component: EditprofileComponent },
  { path: 'editquestion', component: EditquestionComponent },
  { path: 'editanswers', component: EditanswersComponent },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
