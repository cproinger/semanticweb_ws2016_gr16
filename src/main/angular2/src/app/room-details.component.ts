import { Course } from './Course';
import { Room } from './Room';
import { Api } from './api';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'room-details',
  template: `<h2>Room Details</h2>
    <table *ngIf="room != null">
      <tr><td>id: </td><td>{{room.id}}</td></tr>
      <tr><td>name: </td><td>{{room.name}}</td></tr>
      <tr><td>address: </td><td>{{room.address}}</td></tr>
      <tr><td>building: </td><td>{{room.building}}</td></tr>
      <tr><td>capacity: </td><td>{{room.capacity}}</td></tr>
    </table>
    <div *ngIf="errorMessage != null">
      {{errorMessage}}
    </div>
  `,
  styleUrls: ['./courses.component.css'],
  providers: [Api]
})
export class RoomDetailsComponent implements OnInit {
  
  public room;
  public errorMessage : String = "";
  public loaded : boolean = false;
  
  constructor(private api : Api, private route : ActivatedRoute, 
    private router : Router) {
    
  }
  
  ngOnInit() {
    this.route.params.switchMap((params : Params) => 
    this.api.getRoom(params['id']))
      .subscribe(
        cs => {
          this.room = Object.assign(new Room(), cs.json());
        },
        e => this.errorMessage = e,
        () => this.loaded = true
      );
  }
}
