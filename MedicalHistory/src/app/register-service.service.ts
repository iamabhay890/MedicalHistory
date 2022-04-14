import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { User } from './User'

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {

  baseUrl="";
  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string): Observable<Object> {
    return this.httpClient.post<User>(`${this.baseUrl}`, { email, password })
       /* .pipe(map(user => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('user', JSON.stringify(user));
            this.userSubject.next(user);
            return user;
        }));*/
}

  registerUser(user: User): Observable<Object> {
     console.log(user);
     return this.httpClient.post(`${this.baseUrl}`,user);
  }
}