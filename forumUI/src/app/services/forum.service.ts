import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  
  constructor(private http: HttpClient) { }

  getDiscussionsSearchString(urlParameter:string):Observable<any>{
    console.log('Inside forum service');
    const searchStringUrl="//localhost:8080/forum/questions?searchString=";
    return this.http.get<any>(searchStringUrl+urlParameter);
  }
}
