import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Consts} from "../consts";
import 'rxjs/Rx';
import {Rest} from "../rest";
import Task = Domain.Task;
import TaskGroup = Domain.TaskGroup;
import ScheduledTask = Domain.ScheduledTask;

@Injectable()
export class ApiService {

  constructor(private http: Http) {
  }

  getTask() {
    return Rest.get(Consts.TASK, this.http);
  }

  getTaskGroup() {
    return Rest.get(Consts.TASK_GROUP, this.http);
  }

  saveTask(data: Task) {
    return Rest.post(Consts.TASK, data, this.http);
  }

  saveScheduledTask(data: ScheduledTask) {
    return Rest.post(Consts.SCHEDULED_TASK, data, this.http);
  }

  saveTaskGroup(data: TaskGroup) {
    return Rest.post(Consts.TASK_GROUP, data, this.http);
  }
}
