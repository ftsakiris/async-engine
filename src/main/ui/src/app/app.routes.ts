import {RouterModule, Routes} from "@angular/router";
import {TaskComponent} from "./views/task/task.component";
import {ScheduledComponent} from "./views/scheduled/scheduled.component";

const APP_ROUTES: Routes = [
    { path: 'scheduled', component: ScheduledComponent}
  , { path: 'task', component: TaskComponent}
  , { path: '**', redirectTo: '/task', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(APP_ROUTES);
