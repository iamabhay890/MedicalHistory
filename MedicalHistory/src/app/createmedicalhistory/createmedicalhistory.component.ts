import { Component, Inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MedicalForm } from '../medicalform';
import { RegisterServiceService } from '../register-service.service';

@Component({
  selector: 'app-createmedicalhistory',
  templateUrl: './createmedicalhistory.component.html',
  styleUrls: ['./createmedicalhistory.component.css']
})
export class CreatemedicalhistoryComponent implements OnInit {
  medicalform:MedicalForm = new MedicalForm;
  isSucess = false;
  constructor( @Inject(RegisterServiceService)
  private registerService: RegisterServiceService,
  private router: Router) { }

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
    this.registerService.registerUser(this.medicalform).subscribe(
      (data) => {
        this.isSucess=true;
        this.gotoList();
        alert('Successfully form submitted?');
      },
      (error) => this.isSucess=false
    );
    
  }
  gotoList() {
    this.router.navigate(['/home']);
  }
}
