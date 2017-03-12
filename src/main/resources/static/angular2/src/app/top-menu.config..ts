import { CourseDetailsComponent } from './courseDetails.component';
import { CoursesComponent } from './courses.component';
import { RoomDetailsComponent } from './room-details.component';
import { RoomsListComponent } from './rooms-list.component';
import { SemesterCoursesComponent } from './semester-courses.component';
import { Routes } from '@angular/router';

export const routerConfig: Routes = [
    {
        path: 'Courses',
        component: CoursesComponent
    },
    {
        path: 'Courses/:id',
        component: CourseDetailsComponent
    },
    {
        path: 'SemesterCourses',
        component: SemesterCoursesComponent
    },
    {
        path: 'SemesterCourses/:id',
        component: SemesterCoursesComponent
    },
    {
        path: 'Rooms',
        component: RoomsListComponent
    },
    {
        path: 'Rooms/:id',
        component: RoomDetailsComponent
    },
    {
        path: '',
        redirectTo: '/Courses',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/Courses',
        pathMatch: 'full'
    }
];
