import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/primeng";
import {ApiService} from "../../services/api.service";
import ScheduledTask = Domain.ScheduledTask;

@Component({
  selector: 'app-scheduled',
  templateUrl: './scheduled.component.html',
  styleUrls: ['./scheduled.component.css']
})
export class ScheduledComponent implements OnInit {

  scheduledGroupForm: FormGroup;

  msgs: Message[] = [];

  scheduledTask: ScheduledTask = {
    id: "",
    description: "test",
    taskGroupId: "",
    taskId: "",
    cron: "* * * * *",
  };

  constructor(private apiService: ApiService) {
    this.scheduledGroupForm = new FormGroup({
      'description': new FormControl('', Validators.required),
      'taskGroupId': new FormControl(''),
      'taskId': new FormControl('', Validators.required),
      'cron': new FormControl('', Validators.required),
    })
  }

  ngOnInit() {
    // this.getScheduledTask();
    this.initForm();
  }

  initForm() {
    this.scheduledGroupForm.controls['description'].setValue(this.scheduledTask.description);
    this.scheduledGroupForm.controls['taskGroupId'].setValue(this.scheduledTask.taskGroupId);
    this.scheduledGroupForm.controls['taskId'].setValue(this.scheduledTask.taskId);
    this.scheduledGroupForm.controls['cron'].setValue(this.scheduledTask.cron);
  }

  getScheduledTask() {
    this.apiService.getTask().subscribe(
      (data: ScheduledTask) => {
        this.scheduledTask = data;
      },
      null,
      () => this.initForm()
    );
  }

  onSubmit() {
    const value = this.scheduledGroupForm.value;
    this.apiService.saveScheduledTask({
      "id": null,
      "description": value.description,
      taskGroupId: value.taskGroupId,
      taskId: value.taskId,
      cron: value.cron,
    }).subscribe(
      () => {},
      err => {
        this.msgs = [];
        this.msgs.push({severity:'error', summary:'Scheduled Task not saved', detail: err });
      },
      () => {
        this.msgs = [];
        this.msgs.push({severity:'success', summary:'Scheduled Task is saved', detail: '' });
      }
    );
  }

}
