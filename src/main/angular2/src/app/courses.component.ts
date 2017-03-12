import { Course } from './Course';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class CoursesComponent implements OnInit {
  public courses : Array<any>;
  public loaded: boolean = false;
  public errorMessage;

  constructor(private api : Api, private router : Router) {
    
  }
  
  ngOnInit() {
    
    this.api.getCourses()
      .subscribe(
        cs => this.courses = this.toCourseObj(cs.json()),
        e => this.errorMessage = e,
        () => this.loaded = true
      );
  }
  
  toCourseObj(json) : Array<Course> {
    let x = json as Array<any>;
    return x.map(e => Object.assign(new Course(), e));
//    return Object.assign(new Course(), json);
  }
  
  onDetails(c : Course) {
    //return c.links.find(x => x.rel == 'self');
    console.log("goto course  " + c.id);
    this.router.navigate(['/Courses/', c.id]);
  }
}
