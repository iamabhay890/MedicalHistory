import { Component, Inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MedicalForm } from '../medicalform';
import { MedicalhistoryService } from '../medicalhistory.service';
import { RegisterServiceService } from '../register-service.service';

@Component({
  selector: 'app-createmedicalhistory',
  templateUrl: './createmedicalhistory.component.html',
  styleUrls: ['./createmedicalhistory.component.css']
})
export class CreatemedicalhistoryComponent implements OnInit {
  medicalform:MedicalForm = new MedicalForm;
  isSucess = false;
  IconImage:string="/assets/Images/Logo.jpg"
  constructor( @Inject(RegisterServiceService)
  private registerService: RegisterServiceService,
  private medical:MedicalhistoryService,
  private router: Router,
  private toast:ToastrService) { }

  ngOnInit(): void {
  }
  
	TypeofDiseases: Array<any> = [
		{ name: 'Lungs', Disease: [ 'Asthma','Lung cancer','Lung infection'] },
		{ name: 'Heart', Disease: [ 'Heart infection','Heart rhythm problem','Blood vessel disease'] },
		{ name: 'Kidney', Disease: [ 'Chronic kidney disease','Kidney stones','Urinary tract infections'] },
	
	];
    
	Disease: Array<any> = []; 

	changeTypeofDisease(count:any) { 
		console.log(count)
		this.Disease = this.TypeofDiseases.find(con => con.name == count).Disease;
    console.log(this.Disease)
	}
   
  getFormData(f:NgForm) {
    console.log(f);
    this.medical.submitmedicalform(this.medicalform).subscribe(
      (data) => {
        this.isSucess=true;
        this.gotoList();
        this.toast.success('Successfully form submitted!!');
      },
      (error) =>{
        this.toast.error('Something went wrong please try again');
        this.isSucess=false;

      } 
     
    );
    
  }
  gotoList() {
    this.router.navigate(['/home']);
  }
  home(){
    this.router.navigate(['/home']);
  }
}
