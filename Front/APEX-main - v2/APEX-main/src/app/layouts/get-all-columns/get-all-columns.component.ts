import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Column } from 'src/app/model/columns';
import { HttpService } from 'src/app/columnservices/http.service';

@Component({
  selector: 'app-get-all-columns',
  templateUrl: './get-all-columns.component.html',
  styleUrls: ['./get-all-columns.component.css']
})
export class GetAllColumnsComponent implements OnInit{
  constructor(private httpService: HttpService,private router:Router){}
  ListOfColumns:Column[] | undefined;
  ngOnInit(): void {
    this.httpService.getAllColumns().subscribe(Column=>this.ListOfColumns=Column);
  }
  delete(id: string) {
    this.httpService.deleteColumn(id).subscribe();
    this.ngOnInit();
  }
  onEdit(id: string): void {
    this.router.navigate(['columns/edit', id]);
  }

  add(): void {
    this.router.navigate(['columns/create']);
  }

}
