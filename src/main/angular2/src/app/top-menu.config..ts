import { CourseDetailsComponent } from './courseDetails.component';
import { CoursesComponent } from './courses.component';
import { PersonDetailsComponent } from './person-details.component';
import { PersonsListComponent } from './person-list.component';
import { RoomDetailsComponent } from './room-details.component';
import { RoomsListComponent } from './rooms-list.component';
import { SemesterCourseDetailsComponent } from './semester-course-details.component';
import { SemesterCoursesListComponent } from './semester-courses-list.component';
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
        component: SemesterCoursesListComponent
    },
    {
        path: 'SemesterCourses/:id/Semester/:semester',
        component: SemesterCourseDetailsComponent
    },
    {
        path: 'SemesterCourses/:id',
        component: SemesterCoursesListComponent
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
        path: 'Persons',
        component: PersonsListComponent
    },
    {
        path: 'Persons/:id',
        component: PersonDetailsComponent
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
