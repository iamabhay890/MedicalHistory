import { Component, Inject, OnInit } from '@angular/core';
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
 constructor(@Inject(RegisterServiceService) private registerService: RegisterServiceService) { 
    
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
       userLogin(){
        console.log(this.user);
        this.registerService.login(this.user.email,this.user.password).subscribe(data=>{
          this.isSucess=true;
         alert("Successfully User is Login?")
        },error=>location.replace("../home")
        );
      }
    }