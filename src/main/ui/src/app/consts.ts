export class Consts {
  static readonly API_URL: string = 'http://localhost:8083/api';
  // static readonly API_URL: string = window.location.protocol + "//" + window.location.host + '/api';
  static readonly LOG : string = '/log';
  static readonly PENDING : string = '/pending';
  static readonly NUMBERS : string = '/numbers';
  static readonly RETRIEVE : string = '/retrieve';
  static readonly SEND : string = '/send';
  static readonly METADATA : string = '/metadata';
  static readonly PROGRESS : string = '/progress';
  static readonly TASK : string = '/task';
  static readonly CLIENT : string = '/client';
  static readonly BACKOFFICE : string = '/backoffice';
  static readonly DBTYPE : string = '/dbtype';
  static readonly RECEIVE_SEND_PATH : string = '/receive/send';
  static readonly QUERIES : string = '/queries';

  static readonly ProgressBar = require('progressbar.js');
}
