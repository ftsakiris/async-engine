import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './views/header/header.component';
import { AdministrationComponent } from './views/administration/administration.component';
import { SettingsComponent } from './views/settings/settings.component';
import { routing } from "./app.routes";
import { QueriesComponent } from './views/queries/queries.component';
import {ApiService} from "./services/api.service";

import {DataTableModule, SharedModule, BreadcrumbModule, MenuItem, CalendarModule, AccordionModule,
  PanelModule, InputTextModule, RadioButtonModule, TooltipModule, FileUploadModule, GrowlModule,
  PanelMenuModule, DropdownModule, ConfirmDialogModule, ConfirmationService, MessagesModule,
  DialogModule, InputMaskModule
} from 'primeng/primeng';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AdministrationComponent,
    SettingsComponent,
    QueriesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    ReactiveFormsModule,
    DataTableModule,
    SharedModule,
    BreadcrumbModule, CalendarModule, AccordionModule,
    PanelModule, InputTextModule, RadioButtonModule, TooltipModule, FileUploadModule, GrowlModule,
    PanelMenuModule, DropdownModule, ConfirmDialogModule, MessagesModule,
    DialogModule, InputMaskModule
  ],
  providers: [ConfirmationService, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
