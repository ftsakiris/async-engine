import {RouterModule, Routes} from "@angular/router";
import {AdministrationComponent} from "./views/administration/administration.component";
import {SettingsComponent} from "./views/settings/settings.component";
import {QueriesComponent} from "app/views/queries/queries.component";

const APP_ROUTES: Routes = [
    { path: 'settings', component: SettingsComponent}
  , { path: 'administration', component: AdministrationComponent}
  , { path: 'queries', component: QueriesComponent}
  , { path: '**', redirectTo: '/administration', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(APP_ROUTES);
