import { Room } from './Room';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'rooms-list',
  template: `
<div *ngIf="rooms != null">
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Building</th>
        <th>Capacity</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let ro of rooms">
        <td><span class="clickable" (click)="onDetails(ro)">{{ro.id}}</span></td>
        <td>{{ro.name}}</td>
        <td>{{ro.address}}</td>
        <td>{{ro.building}}</td>
        <td>{{ro.capacity}}</td>
      </tr>
    </tbody>
  </table>
</div>
`,
  styleUrls: ['courses.component.css'],
  providers: [Api]
})
export class RoomsListComponent implements OnInit {

  public rooms: Array<any>;
  public loaded: boolean = false;
  public errorMessage: String;

  constructor(private api: Api, private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
      this.api.getRooms()
      .subscribe(
      cs => this.rooms = this.toRoomObj(cs.json()),
      e => this.errorMessage = e,
      () => this.loaded = true
      );
  }

  toRoomObj(json): Array<Room> {
    let x = json as Array<any>;
    return x.map(e => Object.assign(new Room(), e));
    //    return Object.assign(new Course(), json);
  }

  onDetails(r: Room) {
    //return c.links.find(x => x.rel == 'self');
    console.log("goto room  " + r.id);
    this.router.navigate(['/Rooms/', encodeURIComponent(r.id)]);
  }
}
