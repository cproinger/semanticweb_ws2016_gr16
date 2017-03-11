import { Course } from './Course';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'course',
  template: `<h2>courseDetails</h2>
    <table *ngIf="course != null">
      <tr><td>id: </td><td>{{course.id}}</td></tr>
      <tr><td>name: </td><td>{{course.name}}</td></tr>
      <tr><td>semesters: </td><td>{{course.semesters}}</td></tr>
    </table>
    <div *ngIf="errorMessage != null">
      {{errorMessage}}
    </div>
  `,
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class CourseDetailsComponent implements OnInit {
  
  public course;
  public errorMessage : String = "";
  public loaded : boolean = false;
  
  constructor(private api : Api, private route : ActivatedRoute) {
    
  }
  
  ngOnInit() {
    this.route.params.switchMap((params : Params) => 
    this.api.getCourse(params['id']))
      .subscribe(
        cs => {
          this.course = Object.assign(new Course(), cs.json());
          console.log("courseID: " + this.course.id);
        },
        e => this.errorMessage = e,
        () => this.loaded = true
      );
  }
}
