import {RouterModule, Routes} from "@angular/router";
import {TaskComponent} from "./views/task/task.component";
import {ScheduledComponent} from "./views/scheduled/scheduled.component";
import {SettingsComponent} from "./views/settings/settings.component";

const APP_ROUTES: Routes = [
    { path: 'scheduled', component: ScheduledComponent}
  , { path: 'task', component: TaskComponent}
  , { path: 'settings', component: SettingsComponent}
  , { path: '**', redirectTo: '/task', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(APP_ROUTES);
