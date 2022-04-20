import {
  HttpClient,
  HttpClientModule,
  HttpHandler,
} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterServiceService } from './register-service.service';
import { ShowmedicalhistoryComponent } from './showmedicalhistory/showmedicalhistory.component';
import { CreatemedicalhistoryComponent } from './createmedicalhistory/createmedicalhistory.component';
import { HomeComponent } from './home/home.component';
import { StartUpComponent } from './start-up/start-up.component';
import { AuthGuard } from './auth/auth.guard';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    LoginComponent,
    routingComponents,
    ShowmedicalhistoryComponent,
    CreatemedicalhistoryComponent,
    HomeComponent,
    StartUpComponent
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BsDropdownModule.forRoot(),
    ToastrModule.forRoot(),
  ],
  providers: [RegisterServiceService, HttpClient,AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
