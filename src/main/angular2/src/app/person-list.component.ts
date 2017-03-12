import { Person } from './Person';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'persons-list',
  template: `
<div *ngIf="persons != null">
  <table>
    <thead>
      <tr>
        <th>OID</th>
        <th>Title</th>
        <th>Name</th>
        <th>Phone Number</th>
        <th>Email Address</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let p of persons">
        <td><span class="clickable" (click)="onDetails(p)">{{p.oid}}</span></td>
        <td>{{p.title}}</td>
        <td>{{p.name}}</td>
        <td>{{p.phoneNumber}}</td>
        <td>{{p.email}}</td>
      </tr>
    </tbody>
  </table>
</div>
`,
  styleUrls: ['courses.component.css'],
  providers: [Api]
})
export class PersonsListComponent implements OnInit {

  public persons: Array<any>;
  public loaded: boolean = false;
  public errorMessage: String;

  constructor(private api: Api, private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
      this.api.getPersons()
      .subscribe(
      cs => this.persons = this.toPersonObj(cs.json()),
      e => this.errorMessage = e,
      () => this.loaded = true
      );
  }

  toPersonObj(json): Array<Person> {
    let x = json as Array<any>;
    return x.map(e => Object.assign(new Person(), e));
    //    return Object.assign(new Course(), json);
  }

  onDetails(p: Person) {
    //return c.links.find(x => x.rel == 'self');
    console.log("goto room  " + p.oid);
    this.router.navigate(['/Persons/', encodeURIComponent(p.oid)]);
  }
}
