import {RouterModule, Routes} from "@angular/router";
import {TaskComponent} from "./views/task/task.component";
import {ScheduledComponent} from "./views/scheduled/scheduled.component";
import {TaskgroupComponent} from "./views/taskgroup/taskgroup.component";
import {TaskresultComponent} from "./views/taskresult/taskresult.component";

const APP_ROUTES: Routes = [
    { path: 'scheduled', component: ScheduledComponent}
  , { path: 'task', component: TaskComponent}
  , { path: 'taskgroup', component: TaskgroupComponent}
  , { path: 'taskresult', component: TaskresultComponent}
  , { path: '**', redirectTo: '/task', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(APP_ROUTES);
