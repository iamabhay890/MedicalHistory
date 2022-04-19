import { Component, NgModule, OnInit } from '@angular/core';
import { RegisterServiceService } from '../register-service.service';
import { User } from '../User';
import { Inject } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [RegisterServiceService],
})
export class RegistrationComponent implements OnInit {
  user: User = new User();

  isSucess = false;
  constructor(
    @Inject(RegisterServiceService)
    private registerService: RegisterServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {}
  userRegister(f:NgForm) {
    //console.log(this.user);
    this.registerService.registerUser(this.user).subscribe(
      (data) => {
        this.isSucess=true;
        this.gotoList();
        alert('Successfully User is register?');
      },
      (error) => this.isSucess=false
    );
    //this.gotoList();
  }
  gotoList() {
    this.router.navigate(['/home']);
  }
}
