import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/primeng";
import {ApiService} from "../../services/api.service";
import Task = Domain.Task;
import Method = Domain.Method;

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  taskForm: FormGroup;

  msgs: Message[] = [];

  task: Task = {
    id: "",
    description: "test",
    taskGroupId: "",
    headers: null,
    protocol: "http",
    domain: "google.com",
    resourcePath: "",
    port: "80",
    method: "GET",
    queryParams: null,
    body: null,
};

  constructor(private apiService: ApiService) {
    this.taskForm = new FormGroup({
      'description': new FormControl('', Validators.required),
      'taskGroupId': new FormControl(''),
      'protocol': new FormControl('', Validators.required),
      'domain': new FormControl('', Validators.required),
      'headers': new FormControl(''),
      'queryParams': new FormControl(''),
      'resourcePath': new FormControl(''),
      'port': new FormControl('', Validators.required),
      'method': new FormControl('', Validators.required),
      'body': new FormControl(''),
    })
  }

  ngOnInit() {
    // this.getTask();

    this.initForm();
  }

  initForm() {
    this.taskForm.controls['description'].setValue(this.task.description);
    this.taskForm.controls['taskGroupId'].setValue(this.task.taskGroupId);
    this.taskForm.controls['protocol'].setValue(this.task.protocol);
    this.taskForm.controls['domain'].setValue(this.task.domain);
    this.taskForm.controls['headers'].setValue(this.task.headers);
    this.taskForm.controls['queryParams'].setValue(this.task.queryParams);
    this.taskForm.controls['resourcePath'].setValue(this.task.resourcePath);
    this.taskForm.controls['port'].setValue(this.task.port);
    this.taskForm.controls['method'].setValue(this.task.method);
    this.taskForm.controls['body'].setValue(this.task.body);
  }

  getTask() {
    this.apiService.getTask().subscribe(
      (data: Task) => {
        this.task = data;
      },
      null,
      () => this.initForm()
    );
  }

  onSubmit() {
    const value = this.taskForm.value;
    this.apiService.saveTask({
      "id": null,
      "description": value.description,
      "taskGroupId": value.taskGroupId,
      "protocol": value.protocol,
      "domain": value.domain,
      "headers": value.headers,
      "queryParams": value.queryParams,
      "resourcePath": value.resourcePath,
      "port": value.port,
      "method": value.method,
      "body": value.body
    }).subscribe(
      () => {},
      err => {
        this.msgs = [];
        this.msgs.push({severity:'error', summary:'Task not saved', detail: err });
      },
      () => {
        this.msgs = [];
        this.msgs.push({severity:'success', summary:'Task is saved', detail: '' });
      }
    );
  }

}
