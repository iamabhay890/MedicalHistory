import { Component, OnInit } from '@angular/core';
import { RegisterServiceService } from '../register-service.service';
import { User } from '../User';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers:[RegisterServiceService]
})
export class RegistrationComponent implements OnInit {
  user:User =new User();
  
  isSucess=false;
  constructor(@Inject(RegisterServiceService) private registerService: RegisterServiceService) { 
    
  }

  ngOnInit(): void {
  
  }
  userRegister(){
    console.log(this.user);
    this.registerService.registerUser(this.user).subscribe(data=>{
      location.replace("../home")
     alert("Successfully User is register?")
    },error=>location.replace("../home")
    //alert("Sorry User not register")
    );
  }

}
