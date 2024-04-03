import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from 'src/app/columnservices/http.service';

import { Column } from 'src/app/model/columns';


@Component({
  selector: 'app-edit-column',
  templateUrl: './edit-column.component.html',
  styleUrls: ['./edit-column.component.css']
})
export class EditColumnComponent implements OnInit {
  editForm!: FormGroup;
  toEditColumn!: Column;
  id!: string | null;

  constructor(private httpService: HttpService, private fb: FormBuilder, private rout: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.rout.snapshot.paramMap.get('id');
    if (this.id) {
      this.httpService.getColumnById(this.id).subscribe(column => {
        this.toEditColumn = column;
        console.log('Column fetched:', this.toEditColumn);
        this.updateForm();
      });
    } else {
      this.router.navigate(['/columns']);
    }
  }

  updateForm(): void {
    this.editForm = this.fb.group({
      id: [this.toEditColumn.id],
      nom: [this.toEditColumn.nom, Validators.required],
      synonyme: [this.toEditColumn.synonyme,Validators.required],
      type: [this.toEditColumn.type, Validators.required],
      label: [this.toEditColumn.label, Validators.required],
    });
  }

  handelSubmitUpdate(): void {
    if (this.editForm.valid) {
      this.httpService.updateColumnById(this.toEditColumn.id, this.editForm.value).subscribe(
        (response) => {
          console.log('Success:', response);
          this.editForm.reset();
          this.router.navigate(['/columns']);
        },
        (error) => {
          console.log('Error:', error);
        }
      );
    }
  }

  delete(): void {
    if (this.id) {
      this.httpService.deleteColumn(this.id).subscribe(() => {
        console.log('Column deleted successfully');
        this.router.navigate(['/columns']);
      });
    }
  }

  update(): void {
    this.updateForm();
  }
}