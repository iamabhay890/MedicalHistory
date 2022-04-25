import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { RegisterServiceService } from '../register-service.service';
import { User } from '../User';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {
user:User = new User();
isEmail=false;
IconImage:string="/assets/Images/Logo.jpg"
registerView='regView1';
  constructor(private register:RegisterServiceService,
                 private toast:ToastrService) { }

  ngOnInit(): void {
  }

  forgotpassword(f:NgForm){
this.register.forgotpassword(this.user.email).subscribe((data)=>{
this.isEmail=true;
this.toast.success('Sucessfully changed password')
},
error=>{
  this.toast.error('Something went worng please try again');
  this.isEmail=true;
}
)
  }
  changepassword(f:NgForm){
    this.toast.error('Something went worng please try again');
  }

}
