import {Component, OnInit} from "@angular/core";
import {ApiService} from "../../services/api.service";
import {Message} from "primeng/primeng";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  msgs: Message[] = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
  }

}
