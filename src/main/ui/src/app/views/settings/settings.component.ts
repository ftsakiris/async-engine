import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../services/api.service";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {Utils} from "app/utils";
import {Message} from "primeng/primeng";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  settingsForm: FormGroup;

  settings;

  msgs: Message[] = [];

  dbTypes = [];
  backoffices = [];

  constructor(private apiService: ApiService) {
    this.settingsForm = new FormGroup({
        'apiServerName': new FormControl('', Validators.required),
        'dbTypes': new FormControl('', Validators.required),
        'backoffices': new FormControl('', Validators.required),
        'dbName': new FormControl('', Validators.required),
        'dbUserName': new FormControl(''),
        'dbUserPassName': new FormControl(''),
        'dbHostName': new FormControl(''),
        'dbInstanceName': new FormControl(''),
      })
  }

  ngOnInit() {
    this.getBackOffices();
    this.getDbTypes();
    this.getSettings();
  }

  initForm() {
    this.settingsForm.controls['dbTypes'].setValue(this.settings.dbType);
    this.settingsForm.controls['backoffices'].setValue(this.settings.backOffice);
    this.settingsForm.controls['apiServerName'].setValue(this.settings.apiUrl);
    this.settingsForm.controls['dbName'].setValue(this.settings.databaseName);
    this.settingsForm.controls['dbUserName'].setValue(this.settings.userName);
    this.settingsForm.controls['dbUserPassName'].setValue(this.settings.password);
    this.settingsForm.controls['dbHostName'].setValue(this.settings.hostname);
    this.settingsForm.controls['dbInstanceName'].setValue(this.settings.instance);
  }

  getSettings() {
    this.apiService.getSettings().subscribe(
      (data: any) => {
        this.settings = data;
      },
      null,
      () => this.initForm()
    );
  }

  getBackOffices() {
    this.apiService.getBackOffices().subscribe(
      (data: any) => {
        this.backoffices = Utils.convertToArrayFromKey(data);
      },
      null,
      () => this.initForm()
    );
  }

  getDbTypes() {
    this.apiService.getDbTypes().subscribe(
      (data: any) => {
        this.dbTypes = Utils.convertToArrayFromKey(data);
      },
      null,
      () => this.initForm()
    );
  }

  onSubmit() {
    const value = this.settingsForm.value;
    this.apiService.saveSettings({
      "apiUrl": value.apiServerName,
      "backOffice": value.backoffices,
      "databaseName": value.dbName,
      "dbType": value.dbTypes,
      "hostname": value.dbHostName,
      "instance": value.dbInstanceName,
      "password": value.dbUserPassName,
      "userName": value.dbUserName
    }).subscribe(
      () => {},
      err => {
        this.msgs = [];
        this.msgs.push({severity:'error', summary:'Οι ρυθμίσεις Δεν αποθηκεύτηκαν', detail: err });
      },
      () => {
        this.msgs = [];
        this.msgs.push({severity:'success', summary:'Οι ρυθμίσεις αποθηκεύτηκαν', detail: '' });
      }
    );
  }
}
