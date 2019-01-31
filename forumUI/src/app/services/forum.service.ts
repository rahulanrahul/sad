import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models/user.model';

const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  url: string;
  constructor(private http: HttpClient) { }

  postQuestion(question: any): Observable<any> {
    this.url = "//localhost:8080/forum/questions";
    return this.http.post(this.url, question, HttpOptions);
  }

  getDiscussionsSearchString(urlParameter: string): Observable<any> {
    console.log('Inside forum service');
    const searchStringUrl = "//localhost:8080/forum/questions?searchString=";
    return this.http.get<any>(searchStringUrl + urlParameter);
  }

  getDiscussionsByUserId(urlParameter: string): Observable<any> {
    this.url = "//localhost:8080/forum/questions?userId=";
    return this.http.get<any>(this.url + urlParameter);
  }
}
