import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../models/user.model';

const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root'
})
export class UserService {
  url: string;
  constructor(private http: HttpClient) { }

  registerUser(user: User): Observable<any> {
    this.url = "//localhost:8080/users/";
    return this.http.post(this.url, user, HttpOptions);
  }

  getUser(userId: string): Observable<any> {
    this.url = "//localhost:8080/users/";
    this.url = this.url.concat(userId);
    return this.http.get(this.url, HttpOptions);
  }

  updateUser(userId: string, user: User): Observable<any> {
    let params = new HttpParams().set("userId", userId);
    const updateUserHttpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      params: params,
    };
    this.url = "//localhost:8080/users/";
    this.url = this.url.concat(userId);
    return this.http.put(this.url, user, updateUserHttpOptions);
  }

  deleteUser(userId: string): Observable<any> {
    this.url = "//localhost:8080/users/";
    this.url = this.url.concat(userId);
    return this.http.delete(this.url, HttpOptions);
  }
}
