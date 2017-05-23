import {Consts} from "./consts";
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
export class Rest {

  static get(resource: string, http: Http) {
    return http.get(
      Consts.API_URL + resource,
      {
        headers: Rest.getHeaders()
      }
    )
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  static getWithParams(resource: string, params: any, http: Http) {
    return http.get(
      Consts.API_URL + resource,
      {
        headers: Rest.getHeaders(),
        search: params
      }
    )
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  static post(resource: string, data: any, http: Http) {
    const body = JSON.stringify(data);
    return http.post(Consts.API_URL + resource, body, {
      headers: Rest.getHeaders(),
    })
      .map((response: Response) => response)
      .catch(this.handleError);
  }

  static getHeaders() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers;
  }

  static handleError(error: any) {
    console.log(error);
    return Observable.throw(error.json());
  }
}
