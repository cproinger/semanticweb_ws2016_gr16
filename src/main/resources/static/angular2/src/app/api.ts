import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class Api {
  constructor(private http: Http) {
    
  }
  
  getCourses() {
    return this.http.get('http://localhost:8080/courses');
  }
  
  getCourse(id : string) {
    return this.http.get('http://localhost:8080/courses/' + id)
  }
}
