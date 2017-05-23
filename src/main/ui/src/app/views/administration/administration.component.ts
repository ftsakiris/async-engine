import {Component, OnDestroy, OnInit} from "@angular/core";
import {Utils} from "../../utils";
import {Observable} from "rxjs/Observable";
import {ApiService} from "../../services/api.service";
import {Consts} from "../../consts";

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit, OnDestroy {

  consoleLog = '';
  pending = '';
  bar;
  timerSub;

  sendCheckBoxChecked = true;
  retrieveCheckBoxChecked = true;

  status = '';
  readonly IDLE: string = 'IDLE';
  readonly IN_PROGRESS: string = 'IN_PROGRESS';
  readonly COMPLETED: string = 'COMPLETED';
  readonly FAILED: string = 'FAILED';

  lastSync = 'Ποτέ';
  apiServer = '';
  database = '';

  sendData = {
    "isMaterialChecked": false,
    "isTraderChecked": false,
    "isTransactionChecked": false
  };

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.getClientSettings();
    this.getSettings();
    this.getPending();
    this.getStatus();

    let timer = Observable.timer(2000, 2000);
    let self = this;
    this.timerSub = timer.subscribe(() => self.getLog());
  }

  ngOnDestroy(): void {
    this.bar.destroy();
    this.timerSub.unsubscribe();
  }

  getClientSettings() {
    this.apiService.getClientSettings().subscribe(
      (data: any) => {
        this.sendData = {
          "isMaterialChecked": data.isMaterialChecked,
          "isTraderChecked": data.isTraderChecked,
          "isTransactionChecked": data.isTransactionChecked
        };
        this.sendCheckBoxChecked = data.sendCheckBoxChecked;
        this.retrieveCheckBoxChecked = data.retrieveCheckBoxChecked;
      },
      null,
      () => console.log(this.sendData)
    )
  }

  updateClientSettings() {
    this.apiService.updateClientSettings(this.sendData).subscribe();
  }

  getSettings() {
    this.apiService.getSettings().subscribe(
      (data: any) => {
        this.apiServer = data.apiUrl;
        this.database = data.databaseName;
      }
    )
  }

  getStatus() {
    this.apiService.getStatus().subscribe(
      (data: any) => {
        this.status = data.status;
        this.lastSync = data.lastSync;
        this.updateProgressBar(this.status);
      }
    )
  }

  onClickSend() {
    this.status = this.IN_PROGRESS;
    this.apiService.send(this.sendData).subscribe();
  }

  onClickRetrieve() {
    this.status = this.IN_PROGRESS;
    this.apiService.retrieve().subscribe();
  }

  getLog() {
    this.apiService.getConsoleLog().subscribe(
      (data: any) => {
        this.consoleLog = data;
      },
      null,
      () => this.refreshUI()
    );
  }

  isInProgress() {
    return this.status == this.IN_PROGRESS;
  }

  refreshUI() {
    this.getPending();
    this.getStatus();
    this.updateLog();
  }

  getPending() {
    this.apiService.getPendingNumbers().subscribe(
      (data: any) => {
        let pend: string = "";
        pend = pend + "Είδοι: " + data.pendingMaterials + "\n";
        pend = pend + "Barcodes: " + data.pendingBarcodes + "\n";
        pend = pend + "Πελάτες: " + data.pendingCustomers + "\n";
        pend = pend + "Υποκαταστήματα: " + data.pendingBranches + "\n";
        pend = pend + "Προμηθευτές: " + data.pendingSuppliers + "\n";
        pend = pend + "Εμπορικά Παραστατικά: " + data.pendingComDocs + "\n";
        pend = pend + "Οικονομικά Παραστατικά: " + data.pendingFinDocs + "\n";
        pend = pend + "Παραστατικά Αποθήκης: " + data.pendingInvDocs + "\n";
        this.pending = pend;
      }
    );
  }

  updateLog() {
    Utils.textAreaScrollDown('administration-consoleLog');
  }

  updateProgressBar(status: string) {
    let color: string = '';
    if (status == this.IDLE) {
      color = '#72c60d';
    } else if (status == this.IN_PROGRESS) {
      color = '#c6aa09';
    } else if (status == this.COMPLETED) {
      color = '#007bc6';
    } else if (status == this.FAILED) {
      color = '#c60505';
    }

    if (this.bar) {
      this.bar.destroy();
    }
    this.bar = new Consts.ProgressBar.Circle('#container', {
      // strokeWidth: 50,
      strokeWidth: 7,
      easing: 'easeInOut',
      duration: 1400,
      color: color,
      trailColor: color,
      // trailColor: '#eee',
      trailWidth: 5,
      svgStyle: null
    });

    this.bar.setText(status);
    if (status == this.IN_PROGRESS) {
      this.bar.animate(1.0);
    }
  }
}
