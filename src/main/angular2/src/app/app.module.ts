import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/switchMap';
import { RouterModule} from "@angular/router";

import { AppComponent } from './app.component';
import { CourseDetailsComponent } from './courseDetails.component';
import { CoursesComponent } from './courses.component';
import { PersonDetailsComponent } from './person-details.component';
import { PersonsListComponent } from './person-list.component';
import { RoomDetailsComponent } from './room-details.component';
import { RoomsListComponent } from './rooms-list.component';
import { SemesterCourseDetailsComponent } from './semester-course-details.component';
import { SemesterCoursesListComponent } from './semester-courses-list.component';
import { TopMenuComponent } from './top-menu.component';
import { routerConfig } from './top-menu.config.';

@NgModule({
  declarations: [
    AppComponent, CoursesComponent, SemesterCoursesListComponent, 
    TopMenuComponent, SemesterCourseDetailsComponent,
    CourseDetailsComponent, RoomsListComponent, RoomDetailsComponent,
    PersonsListComponent, PersonDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routerConfig)
  ],
  exports: [
  	RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
