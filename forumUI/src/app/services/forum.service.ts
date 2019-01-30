import { Injectable } from '@angular/core';
<<<<<<< HEAD
import { HttpClient, HttpResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../models/user.model';

const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
=======
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
>>>>>>> branch 'master' of https://github.com/infomediadesign/sad2019-1-team-14.git

@Injectable({
  providedIn: 'root'
})
export class ForumService {
<<<<<<< HEAD
  url: string;
=======
  
>>>>>>> branch 'master' of https://github.com/infomediadesign/sad2019-1-team-14.git
  constructor(private http: HttpClient) { }

<<<<<<< HEAD
  postQuestion(question: any): Observable<any> {
    this.url = "//localhost:8080/forum/questions";
    return this.http.post(this.url, question, HttpOptions);
=======
  getDiscussionsSearchString(urlParameter:string):Observable<any>{
    console.log('Inside forum service');
    const searchStringUrl="//localhost:8080/forum/questions?searchString=";
    return this.http.get<any>(searchStringUrl+urlParameter);
>>>>>>> branch 'master' of https://github.com/infomediadesign/sad2019-1-team-14.git
  }
}
