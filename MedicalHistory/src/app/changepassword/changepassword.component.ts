import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RegisterServiceService } from '../register-service.service';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {
  isSucess=false;
  email!:string;
  oldpassword!:string;
  newpassword!:string;
  IconImage:string="/assets/Images/Logo.jpg"
  constructor(private registerservice:RegisterServiceService,private router:Router,private toast:ToastrService) { }

  ngOnInit(): void {
  }

  getchangepassword(){
    //console.log(f);
    this.registerservice.changepassword(this.email,this.oldpassword,this.newpassword).subscribe((data:any)=>{
     // localStorage.setItem('userToken',data.access_token);
      this.isSucess=true
      this.toast.success("password changed sucessfully");
      this.router.navigate(['/home']);
    },
    (error)=>this.toast.error("Something went wrong please try again"));
   
  }
  home(){
    this.router.navigate(['/home']);
  }

}
