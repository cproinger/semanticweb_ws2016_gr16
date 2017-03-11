import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { RouterModule} from "@angular/router";

import { AppComponent } from './app.component';
import { CoursesComponent } from './courses.component';
import { SemesterCoursesComponent } from './semester-courses.component';
import { TopMenuComponent } from './top-menu.component';
import { routerConfig } from './top-menu.config.';

@NgModule({
  declarations: [
    AppComponent, CoursesComponent, SemesterCoursesComponent, TopMenuComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routerConfig)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
