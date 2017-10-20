export class Consts {
  // static readonly API_URL: string = 'http://localhost:8081/api';
  static readonly API_URL: string = window.location.protocol + "//" + window.location.host + '/api';

  static readonly TASK : string = '/task';
  static readonly TASK_RESULT : string = Consts.TASK + '/result';
  static readonly FIND_BY_TASK_ID_PATH : string = Consts.TASK_RESULT + '/search/findByTaskId';
  static readonly TASK_GROUP : string = Consts.TASK + '/group';
  static readonly SCHEDULED_TASK : string = '/scheduledTask';

  static readonly ProgressBar = require('progressbar.js');
}
