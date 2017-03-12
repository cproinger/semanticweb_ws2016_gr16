import { Course } from './Course';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SemesterCourse } from './SemesterCourse';

@Component({
  selector: 'semester-courses',
  template: `
<div *ngIf="courses != null">
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>ECTS</th>
        <th>Semester Hours</th>
        <th>Semester</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let co of courses">
        <td><span class="clickable" (click)=onDetails(co)>{{co.course.id}}</span></td>
        <td>{{co.course.name}}</td>
        <td>{{co.course.ects}}</td>
        <td>{{co.course.semesterHours}}</td>
        <td>{{co.semester}}</td>
      </tr>
    </tbody>
  </table>
</div>
`,
  styleUrls: ['courses.component.css'],
  providers: [Api]
})
export class SemesterCoursesListComponent implements OnInit {

  public courses: Array<any>;
  public loaded: boolean = false;
  public errorMessage: String;

  constructor(private api: Api, private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.route.params.switchMap((params: Params) =>
      this.api.getSemesterCourses(params['id']))
      .subscribe(
      cs => this.courses = this.toCourseObj(cs.json()),
      e => this.errorMessage = e,
      () => this.loaded = true
      );
  }

  toCourseObj(json): Array<SemesterCourse> {
    let x = json as Array<any>;
    return x.map(e => Object.assign(new SemesterCourse(), e));
    //    return Object.assign(new Course(), json);
  }

  onDetails(c: SemesterCourse) {
    //return c.links.find(x => x.rel == 'self');
    console.log("goto semester-course  " + c.course.id);
    this.router.navigate([
      'SemesterCourses', 
      c.course.id, 
      'Semester', 
      c.semester
    ]);
  }
}
