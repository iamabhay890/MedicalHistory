import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, map, Observable, Observer } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './User'

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {
  //private userSubject: BehaviorSubject<User>;
   // public user: Observable<User>;
   USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  baseUrl=" http://localhost:8080/api/users/";
  constructor(private httpClient: HttpClient, 
                            private router: Router) { 
    
  } 

  login(email: string, password: string): Observable<Object> {
     var data="email="+email+"&password="+password+"&grand_type=password";
     console.log(data);
     var reqHeader=new HttpHeaders({'Content-Type':'application/x-www.urlencoded'});
    return this.httpClient.post<User>(`${this.baseUrl}/token`, data,{ headers:reqHeader })
    
}
loggingout() {
    localStorage.removeItem('userToken');
    this.router.navigate(['/medicalhistory']);
  }
  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }
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