import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicalForm } from '../medicalform';
import { RegisterServiceService } from '../register-service.service';
;

@Component({
  selector: 'app-showmedicalhistory',
  templateUrl: './showmedicalhistory.component.html',
  styleUrls: ['./showmedicalhistory.component.css']
})
export class ShowmedicalhistoryComponent implements OnInit {
  
medicine:MedicalForm = new MedicalForm;
IconImage:string="/assets/Images/Logo.jpg"

  constructor(private httpClient: HttpClient,private registerService:RegisterServiceService,
                               private router:Router) { }

  public dataa = [
    {
      doctorname: this.medicine.doctorname, 
      hospitalname: this.medicine.doctorname, 
      patientage:this.medicine.doctorname,
      typeofdisease:this.medicine.typeofdisease,
      description:this.medicine.description,
      treatmentdate:this.medicine.treatmentdate,
      nextappointment:this.medicine.nextappointment,
      medicinename:this.medicine.medicinename
  }
  ];
  dtOptions: DataTables.Settings = {};
  ngOnInit() {
  this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
    lengthMenu : [5, 10],
      processing: true
    };
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
 home(){
   this.router.navigate(['/home']);
 }
}

