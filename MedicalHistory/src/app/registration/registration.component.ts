import { Component, NgModule, OnInit } from '@angular/core';
import { RegisterServiceService } from '../register-service.service';
import { User } from '../User';
import { Inject } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [RegisterServiceService],
})
export class RegistrationComponent implements OnInit {
  user: User = new User();
  IconImage:string="/assets/Images/Logo.jpg";
  isSucess = false;
  constructor(
    @Inject(RegisterServiceService)
    private registerService: RegisterServiceService,
    private router: Router,
    private toast:ToastrService
  ) {}

  ngOnInit(): void {}
  userRegister(f:NgForm) {
    //console.log(this.user);
    this.registerService.registerUser(this.user).subscribe(
      (data) => {
        this.isSucess=true;
        this.gotoList();
        this.toast.success('Successfully User is register');
      },
      (error) => this.toast.error('Something went wrong please try again')
    );
    //this.gotoList();
  }
  gotoList() {
    this.router.navigate(['/home']);
  }
}
