import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable, Observer } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './User'

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {
  //private userSubject: BehaviorSubject<User>;
   // public user: Observable<User>;
  baseUrl=" http://localhost:8080/api/users/";
  constructor(private httpClient: HttpClient, private router: Router) { 
    //this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    //this.user = this.userSubject.asObservable();
  } 

 /* public get userValue(): User {
    return this.userSubject.value;
}*/

  login(email: string, password: string): Observable<Object> {
    return this.httpClient.post<User>(`${this.baseUrl}`, { email, password })
      /*  .pipe(map(user => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('user', JSON.stringify(user));
            this.userSubject.next(user);
            return user;
        }));*/
}

/*loggingout(){
  localStorage.removeItem('user');
  this.userSubject.next(null);
  this.router.navigate(['/account/login']);
}*/


  registerUser(user: Object): Observable<Object> {
     console.log(user);
     return this.httpClient.post(`${this.baseUrl}`,user);
  }

  getAll() {
    return this.httpClient.get<User[]>(`${this.baseUrl}/users`);
}

getById(id: string) {
    return this.httpClient.get<User>(`${this.baseUrl}/users/${id}`);
}

/*update(id, params) {
    return this.httpClient.put(`${this.baseUrl}/users/${id}`, params)
        .pipe(map(x => {
            // update stored user if the logged in user updated their own record
            if (id == this.userValue.id) {
                // update local storage
                const user = { ...this.userValue, ...params };
                localStorage.setItem('user', JSON.stringify(user));

                // publish updated user to subscribers
                this.userSubject.next(user);
            }
            return x;
        }));
}*/

/*delete(id: string) {
    return this.httpClient.delete(`${this.baseUrl}${id}`)
        .pipe(map(x => {
            // auto logout if the logged in user deleted their own record
            if (id == this.userValue.id) {
                this.loggingout();
            }
            return x;
        }));
}*/
}