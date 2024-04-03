import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActionLog } from 'src/app/models/ActionLog';
import { ActionLogService } from 'src/app/services/action-log.service';

@Component({
  selector: 'app-action-log',
  templateUrl: './action-log.component.html',
  styleUrls: ['./action-log.component.css']
})
export class ActionLogComponent {
  logs: ActionLog[] = [];
  constructor(private actionLogService: ActionLogService) { }
    ngOnInit(): void {
      this.fetchLogs();


    }

    fetchLogs() {
      this.actionLogService.getAllActionLogs().subscribe({
        next: (data) => {
          this.logs = data;
        },
        error: (error) => console.log(error),
        complete: () => console.log('done')
      });
    }

}
