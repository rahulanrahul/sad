import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from '../models/user.model';

const registerHttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
let params = new HttpParams().set("userId", "12");
const getUserHttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root'
})
export class UserService {
  url: string;
  constructor(private http: HttpClient) { }

  register(user: User): Observable<any> {
    return this.http.post(this.url, user, registerHttpOptions);
  }

  getUser(userId: number): Observable<any> {
    console.log(userId);
    this.url = "//localhost:8080/users/";
    this.url = this.url.concat(userId.toString());
    console.log(this.url);
    return this.http.get(this.url, getUserHttpOptions);
  }
}
