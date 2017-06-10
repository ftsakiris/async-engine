import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../services/api.service";
import TaskResult = Domain.TaskResult;
import Task = Domain.Task;
import {TreeNode, Message} from 'primeng/primeng';

@Component({
  selector: 'app-taskresult',
  templateUrl: './taskresult.component.html',
  styleUrls: ['./taskresult.component.css']
})
export class TaskresultComponent implements OnInit {

  taskResults: TaskResult[] = [];

  tasks: TreeNode[];

  selectedTask: TreeNode[];

  msgs: Message[] = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {

    let that = this;
    that.getTasks();
  }

  nodeExpand(event) {
    if (event.node) {
      //in a real application, make a call to a remote url to load children of the current node and add the new nodes as children
      this.getTaskResults(event.node, event.node.data.id);
    }
  }

  getTaskResults(node, taskId: string) {
    this.apiService.findTaskResultsByTaskId(taskId).subscribe(
      (data: TaskResult[]) => {
        var taskResults = [];
        data.forEach(taskResult => {
          taskResults.push(
            {"data":
              {
                description: taskResult.id,
                taskResult: taskResult.taskResult
              }
            })
        });
        node.children = taskResults;
      },
      null,
      null
    );
  }

  getTasks() {
    this.apiService.getTasks().subscribe(
      (data: Task[]) => {
        var tasks = [];
        data.forEach(task => {
          tasks.push(
            {"data":
              {
                id: task.id,
                description: task.description,
                taskResult: ""
              },
              "leaf": false
            }
            )
        });
        this.tasks = tasks;
      },
      null,
      null
    );
  }

}
