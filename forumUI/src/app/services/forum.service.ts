import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

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
    return this.http.get<any[]>(searchStringUrl + urlParameter, HttpOptions);
  }

  getDiscussionsCategory(urlParameter: string): Observable<any> {
    console.log('Inside forum service');
    const searchStringUrl = "//localhost:8080/forum/questions?category=";
    return this.http.get<any[]>(searchStringUrl + urlParameter, HttpOptions);
  }


  getDiscussionsByUserId(urlParameter: string): Observable<any> {
    this.url = "//localhost:8080/forum/questions?userId=";
    return this.http.get<any>(this.url + urlParameter);
  }

  deleteQuestion(questionId: number): Observable<any> {
    this.url = "//localhost:8080/forum/questions/";
    this.url = this.url.concat(questionId.toString());
    return this.http.delete(this.url, HttpOptions);
  }

  closeQuestion(questionId: number): Observable<any> {
    this.url = "//localhost:8080/forum/questions/close-thread/";
    this.url = this.url.concat(questionId.toString());
    return this.http.put(this.url, HttpOptions);
  }

  updateQuestion(questionId: number, question: any): Observable<any> {
    let params = new HttpParams().set("questionId", questionId.toString());
    const updateQuestionHttpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      params: params,
    };
    this.url = "//localhost:8080/forum/questions/";
    this.url = this.url.concat(questionId.toString());
    console.log(this.url);
    return this.http.put(this.url, question, updateQuestionHttpOptions);
  }
}
