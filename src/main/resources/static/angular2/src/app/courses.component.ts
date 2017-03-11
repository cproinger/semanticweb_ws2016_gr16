import { Api } from './api';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class CoursesComponent implements OnInit {
  public courses;
  public loaded: boolean = false;
  public errorMessage;

  constructor(private _api : Api) {
    
  }
  
  ngOnInit() {
    this.courses = this._api.getCourses()
      .subscribe(
        cs => this.courses = cs.json(),
        e => this.errorMessage = e,
        () => this.loaded = true
      );
  }
}
