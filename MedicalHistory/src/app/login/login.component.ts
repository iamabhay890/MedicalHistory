import { Component, Inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import {  ToastrService } from 'ngx-toastr';
import { RegisterServiceService } from '../register-service.service';
import {User } from '../User';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 user:User =new User();
 isSucess=false;
 constructor(@Inject(RegisterServiceService) private registerService: RegisterServiceService,
                                 private router: Router,
                                 private toast:ToastrService) { 
    
}



    ngOnInit() {
        
    }

    // convenience getter for easy access to form fields
    //get f() { return this.form.controls; }

    onSubmit() {
        //this.submitted = true;

        // reset alerts on submit
       // this.alertService.clear();

        // stop here if form is invalid
        //if (this.form.invalid) {
         //   return;
        }

       // this.loading = true;
       userLogin(f:NgForm){
        console.log(this.user);
        this.registerService.login(this.user.email,this.user.password).subscribe((data:any)=>{
          localStorage.setItem('userToken',data.access_token);
          this.isSucess=true
          this.gotoList();
          this.toast.success("Logged in sucessfully");
        },
        (error)=>this.toast.error("Something went wrong please try again"));
        
        //this.isSucess=false);
        
      }
      gotoList() {
        this.router.navigate(['/home']);
        
      }
    }