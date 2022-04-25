import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { MedicalDetailsService } from '../medical-details.service';
import { RegisterServiceService } from '../register-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private registerService:RegisterServiceService, private router:Router,
    private medical:MedicalDetailsService) {}
 
myImage:string="/assets/Images/startupimg.jpg";
IconImage:string="/assets/Images/Logo.jpg"

topHeadlines:any=[];

  ngOnInit(): void {
//this.registerService.topHeadlines().subscribe((data)=>{
//this.topHeadlines=data.articles;
  //console.log(data);
//})
  }
  logout() {
   this.registerService.loggingout();
  
  }
  changepassword(){
    this.router.navigate(['/changepassword']);
  }
  profile(){
    this.router.navigate(['/profile']);
  }

 
}
