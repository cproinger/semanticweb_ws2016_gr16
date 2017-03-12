import { Course } from './Course';
import { Person } from './Person';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'person-details',
  template: `<h2>Person Details</h2>
    <table *ngIf="person != null">
      <tr><td>image:</td><td><img src="{{person.imageUri}}"></td></tr>
      <tr><td>oid: </td><td>{{person.oid}}</td></tr>
      <tr><td>Title: </td><td>{{person.title}}</td></tr>
      <tr><td>name: </td><td>{{person.name}}</td></tr>
      <tr><td>Phone Number: </td><td>{{person.phoneNumber}}</td></tr>
      <tr><td>Email: </td><td><a href="mailto:{{person.email}}">{{person.email}}</a></td></tr>
    </table>
    <div *ngIf="errorMessage != null">
      {{errorMessage}}
    </div>
  `,
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class PersonDetailsComponent implements OnInit {

  public person;
  public errorMessage : String = "";
  public loaded : boolean = false;

  constructor(private api : Api, private route : ActivatedRoute,
    private router : Router) {

  }

  ngOnInit() {
    this.route.params.switchMap((params : Params) =>
    this.api.getPerson(params['id']))
      .subscribe(
        cs => {
          this.person = Object.assign(new Person(), cs.json());
        },
        e => this.errorMessage = e,
        () => this.loaded = true
      );
  }
}
