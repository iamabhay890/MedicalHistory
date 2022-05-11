import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { CreatemedicalhistoryComponent } from './createmedicalhistory/createmedicalhistory.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { StartUpComponent } from './start-up/start-up.component';

const routes: Routes = [
  {path:'signup',component: RegistrationComponent},
  {path:'login',component: LoginComponent},
  {path: 'home', component: HomeComponent},//,canActivate:[AuthGuard]
  {path: 'medicalhistory', component: StartUpComponent},
  {path:'create',component:CreatemedicalhistoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [RegistrationComponent,LoginComponent,HomeComponent];
