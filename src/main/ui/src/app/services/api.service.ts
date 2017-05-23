import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Consts} from "../consts";
import 'rxjs/Rx';
import {Rest} from "../rest";

@Injectable()
export class ApiService {

  constructor(private http: Http) {
  }

  getClientSettings() {
    return Rest.get(Consts.SETTINGS + Consts.CLIENT, this.http);
  }

  updateClientSettings(data: any) {
    return Rest.post(Consts.SETTINGS + Consts.CLIENT, data, this.http);
  }

  updateClientSettingsRS(data: any) {
    return Rest.post(Consts.SETTINGS + Consts.CLIENT + Consts.RECEIVE_SEND_PATH, data, this.http);
  }

  getSettings() {
    return Rest.get(Consts.SETTINGS, this.http);
  }

  getStatus() {
    return Rest.get(Consts.METADATA + Consts.PROGRESS, this.http);
  }

  getConsoleLog() {
    return Rest.get(Consts.LOG, this.http);
  }

  getPendingNumbers() {
    return Rest.get(Consts.PENDING + Consts.NUMBERS, this.http);
  }

  retrieve() {
    return Rest.get(Consts.RETRIEVE, this.http);
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

  saveSettings(data: any) {
    return Rest.post(Consts.SETTINGS, data, this.http);
  }

  getQueries() {
    return Rest.get(Consts.QUERIES, this.http);
  }

  saveQuery(data: any) {
    return Rest.post(Consts.QUERIES, data, this.http);
  }
}
