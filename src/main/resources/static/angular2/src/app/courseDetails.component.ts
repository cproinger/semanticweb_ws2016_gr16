import { Course } from './Course';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'course',
  template: '<h2>courseDetails</h2>',
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class CourseDetailsComponent implements OnInit {
  
  public course : Course;
  public errorMessage : String;
  public loaded : boolean;
  
  constructor(private api : Api, private route : ActivatedRoute) {
    
  }
  
  ngOnInit() {
    
//    this.course = this.api.getCourse()
//      .subscribe(
//        cs => this.course = this.toCourseObj(cs.json()),
//        e => this.errorMessage = e,
//        () => this.loaded = true
//      );
  }
}
