import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterServiceService } from '../register-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private registerService:RegisterServiceService, private router:Router) {}

  ngOnInit(): void {}
  logout() {
   this.registerService.loggingout();
  
  }
}
