import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/primeng";
import TaskGroup = Domain.TaskGroup;
import {ApiService} from "../../services/api.service";

@Component({
  selector: 'app-taskgroup',
  templateUrl: './taskgroup.component.html',
  styleUrls: ['./taskgroup.component.css']
})
export class TaskgroupComponent implements OnInit {

  taskGroupForm: FormGroup;

  msgs: Message[] = [];

  taskGroup: TaskGroup = {
    id: null,
    description: "test"
  };

  constructor(private apiService: ApiService) {
    this.taskGroupForm = new FormGroup({
      'description': new FormControl('', Validators.required),
    })
  }

  ngOnInit() {
    // this.getTaskGroup();
    this.initForm();
  }

  initForm() {
    this.taskGroupForm.controls['description'].setValue(this.taskGroup.description);
  }

  getTaskGroup() {
    this.apiService.getTaskGroups().subscribe(
      (data: TaskGroup) => {
        this.taskGroup = data;
      },
      null,
      () => this.initForm()
    );
  }

  onSubmit() {
    const value = this.taskGroupForm.value;
    this.apiService.saveTaskGroup({
      "id": null,
      "description": value.description
    }).subscribe(
      () => {},
      err => {
        this.msgs = [];
        this.msgs.push({severity:'error', summary:'Task Group not saved', detail: err });
      },
      () => {
        this.msgs = [];
        this.msgs.push({severity:'success', summary:'Task Group is saved', detail: '' });
      }
    );
  }
}
