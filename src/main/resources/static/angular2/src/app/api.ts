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
  
  getSemesterCourses(id: string) {
    var path = id == null ? '' : id + '/'
    return this.http.get('http://localhost:8080/semesterCourses/' + path)
  }
  
  getSemesterCourse(id: string, semester: string) {
    return this.http.get('http://localhost:8080/semesterCourses/' 
      + id + '/semester/' + semester)
  }
  
  getRooms() {
    return this.http.get('http://localhost:8080/rooms');
  }
  
  getRoom(id: string) {
    if(id.indexOf(' ') > 0)
      id = encodeURIComponent(id);
    return this.http.get('http://localhost:8080/rooms/' + id);
  }
}
