import { CoursesComponent } from './courses.component';
import { SemesterCoursesComponent } from './semester-courses.component';
import { Routes } from '@angular/router';

export const routerConfig: Routes = [
    {
        path: 'Courses',
        component: CoursesComponent
    },
    {
        path: 'SemesterCourses',
        component: SemesterCoursesComponent
    },
    {
        path: 'courses2',
        component: CoursesComponent
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
