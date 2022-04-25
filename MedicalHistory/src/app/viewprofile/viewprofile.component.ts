import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { RegisterServiceService } from '../register-service.service';
import { User } from '../User';

@Component({
  selector: 'app-viewprofile',
  templateUrl: './viewprofile.component.html',
  styleUrls: ['./viewprofile.component.css']
})
export class ViewprofileComponent implements OnInit {
user:User = new User();
Phone:any;
Name:any;
Email:any;
IconImage:string="/assets/Images/Logo.jpg"
  constructor(private route:Router,private userservice:RegisterServiceService) { }

  ngOnInit(): void {
      const useremail:any = localStorage.getItem('email')
         this.userservice.getByEmail(useremail).subscribe((data)=>{
           this.Phone=data.phone;
           this.Name=data.name;
           this.Email=data.email;
         });
  }
 
  editprofile(){
    this.route.navigate(['/home']);
  }
  home(){
    this.route.navigate(['/home']);
  }

}
