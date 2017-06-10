import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './views/header/header.component';
import { routing } from "./app.routes";
import {ApiService} from "./services/api.service";

import {
  DataTableModule, SharedModule, BreadcrumbModule, MenuItem, CalendarModule, AccordionModule,
  PanelModule, InputTextModule, RadioButtonModule, TooltipModule, FileUploadModule, GrowlModule,
  PanelMenuModule, DropdownModule, ConfirmDialogModule, ConfirmationService, MessagesModule,
  DialogModule, InputMaskModule, TreeModule, TreeTableModule
} from 'primeng/primeng';
import { TaskComponent } from './views/task/task.component';
import { ScheduledComponent } from './views/scheduled/scheduled.component';
import { TaskgroupComponent } from './views/taskgroup/taskgroup.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { TaskresultComponent } from './views/taskresult/taskresult.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TaskComponent,
    ScheduledComponent,
    TaskgroupComponent,
    TaskresultComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    ReactiveFormsModule,
    DataTableModule,
    SharedModule,
    BreadcrumbModule, CalendarModule, AccordionModule, TreeTableModule,
    PanelModule, InputTextModule, RadioButtonModule, TooltipModule, FileUploadModule, GrowlModule,
    PanelMenuModule, DropdownModule, ConfirmDialogModule, MessagesModule,
    DialogModule, InputMaskModule
  ],
  providers: [ConfirmationService, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
