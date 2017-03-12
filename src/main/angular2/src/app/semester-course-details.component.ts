import { SemesterCourse } from './SemesterCourse';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'semesterCourse',
  template: `<h2>SemesterCourse Details</h2>
    <div *ngIf="semesterCourse != null">
    <table>
      <tr><td>id: </td><td>{{semesterCourse.course.id}}</td></tr>
      <tr><td>type: </td><td>{{semesterCourse.course.type}}</td></tr>
      <tr><td>name: </td><td>{{semesterCourse.course.name}}</td></tr>
      <tr><td>semester: </td><td>{{semesterCourse.semester}}</td></tr>
      <tr><td>headOfCourse: </td><td><span class="clickable" (click)="onPersonDetails(semesterCourse.headOfCourse.substring(63, semesterCourse.headOfCourse.Length))">{{semesterCourse.headOfCourse.substring(63, semesterCourse.headOfCourse.Length)}}</span></td></tr>
    </table>
    <h3>Rooms</h3>
    <table *ngFor="let r of semesterCourse.rooms">
      <tr>
        <td><span class="clickable" (click)="onRoomDetails(r)">{{r}}</span></td>
      </tr>
    </table>
    </div>
    <div>
      <span class="clickable" (click)="onCourseDetails()">go to Course Details</span>
    </div>
    <div *ngIf="errorMessage != null">
      {{errorMessage}}
    </div>
  `,
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class SemesterCourseDetailsComponent implements OnInit {

  public semesterCourse;
  public errorMessage : String = "";
  public loaded : boolean = false;
  private sub : Observable<any>;

  constructor(private api : Api, private route : ActivatedRoute,
    private router : Router) {

  }

  ngOnInit() {
    this.route.params.switchMap((params : Params) =>
    this.sub = this.api.getSemesterCourse(params['id'], params['semester']))
      .subscribe(
        cs => {
          this.semesterCourse = Object.assign(new SemesterCourse(), cs.json());
        },
        e => this.errorMessage = e,
        () => this.loaded = true
      );
  }

  onCourseDetails() {
    this.router.navigate(['/Courses/', this.semesterCourse.course.id]);
  }
  onRoomDetails(r : string) {
    this.router.navigate(['Rooms', encodeURIComponent(r)]);
  }
  onPersonDetails(p : string) {
    this.router.navigate(['/Persons/', encodeURIComponent(p)]);
  }
}
