import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicalhistoryService {

  baseUrl="http://localhost:8080/api/users/";
 
  constructor(private httpClient: HttpClient, 
    private router: Router) { }


    submitmedicalform(medicalform: Object): Observable<Object> {
      console.log(medicalform);
      return this.httpClient.post(`${this.baseUrl}`,medicalform);
   }
}
