import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { CreatemedicalhistoryComponent } from './createmedicalhistory/createmedicalhistory.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ShowmedicalhistoryComponent } from './showmedicalhistory/showmedicalhistory.component';
import { StartUpComponent } from './start-up/start-up.component';
import { ViewprofileComponent } from './viewprofile/viewprofile.component';

const routes: Routes = [
  {path:'signup',component: RegistrationComponent},
  {path:'login',component: LoginComponent},
  {path: 'home', component: HomeComponent},//,canActivate:[AuthGuard]
  {path: 'medicalhistory', component: StartUpComponent},
  {path:'create',component:CreatemedicalhistoryComponent},//,canActivate:[AuthGuard]
  {path:'changepassword',component:ChangepasswordComponent},//,canActivate:[AuthGuard]
  {path:'profile',component:ViewprofileComponent},//,canActivate:[AuthGuard]
  {path:'forgotpassword',component:ForgotpasswordComponent},
  {path:'show',component:ShowmedicalhistoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [RegistrationComponent,LoginComponent,HomeComponent];
