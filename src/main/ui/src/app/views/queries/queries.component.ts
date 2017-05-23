import { Component, OnInit } from '@angular/core';
import {Query} from "app/views/queries/query.model";
import {ApiService} from "../../services/api.service";
import {Message} from "primeng/primeng";

@Component({
  selector: 'app-queries',
  templateUrl: './queries.component.html',
  styleUrls: ['./queries.component.css']
})
export class QueriesComponent implements OnInit {

  constructor(private apiService: ApiService) { }

  queries: Query[] = [];

  selectedQuery: Query;
  selectedQueryKey: string = '';
  selectedQueryValue: string = '';

  displayEditQuery: boolean = false;

  msgs: Message[] = [];

  ngOnInit() {

    let that = this;
    that.refresh();
  }

  saveQuery() {
    this.msgs = [];

    console.log(this.selectedQuery);
    this.closeEditQueryDialog();
    this.apiService.saveQuery(new Query(this.selectedQuery.key, this.selectedQueryValue)).subscribe(
      null,
      err => {
        this.msgs = [];
        this.msgs.push({severity:'error', summary:'Query Not Saved', detail: err });
      },
      () => {
        this.msgs = [];
        this.msgs.push({severity:'success', summary:'Query Saved', detail: this.selectedQuery.key });
        let that = this;
        that.refresh();
      }
    )
  }

  refresh() {
    this.apiService.getQueries().subscribe(
      (queries: Query[]) => this.queries = queries,
      err => {
        this.msgs = [];
        this.msgs.push({severity:'error', summary:'Error', detail: err });
      },
      () => {
        this.msgs = [];
        this.msgs.push({severity:'info', summary:'Data Refreshed', detail: "" });
      }
    );
  }

  showEditQueryDialog(event) {
    this.selectedQueryKey = event.data.key;
    this.selectedQueryValue = event.data.value;
    this.displayEditQuery = true;
  }

  closeEditQueryDialog() {
    this.displayEditQuery = false;
  }
}
