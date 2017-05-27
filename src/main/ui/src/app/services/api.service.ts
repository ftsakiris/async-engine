import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Consts} from "../consts";
import 'rxjs/Rx';
import {Rest} from "../rest";
import Task = Domain.Task;

@Injectable()
export class ApiService {

  constructor(private http: Http) {
  }

  getTask() {
    return Rest.get(Consts.TASK, this.http);
  }

  getStatus() {
    return Rest.get(Consts.METADATA + Consts.PROGRESS, this.http);
  }

  getConsoleLog() {
    return Rest.get(Consts.LOG, this.http);
  }

  send(data: any) {
    return Rest.post(Consts.SEND, data, this.http);
  }

  getBackOffices() {
    return Rest.get(Consts.METADATA + Consts.BACKOFFICE, this.http);
  }

  getDbTypes() {
    return Rest.get(Consts.METADATA + Consts.DBTYPE, this.http);
  }

  saveTask(data: Task) {
    return Rest.post(Consts.TASK, data, this.http);
  }

  getQueries() {
    return Rest.get(Consts.QUERIES, this.http);
  }

  saveQuery(data: any) {
    return Rest.post(Consts.QUERIES, data, this.http);
  }
}
