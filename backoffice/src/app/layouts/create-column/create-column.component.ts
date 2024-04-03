import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from 'src/app/columnservices/http.service';

@Component({
  selector: 'app-create-column',
  templateUrl: './create-column.component.html',
  styleUrls: ['./create-column.component.css']
})
export class CreateColumnComponent implements OnInit {
  createForm!: FormGroup;

  constructor(private httpService: HttpService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.createForm = this.fb.group({
      nom: [''],
      synonyme: [''],
      type: [''],
      label: [''],
      businessKey: [''],
    });
  }

  handelSubmit(): void {
    if (this.createForm.valid) {
      this.httpService.addColumn(this.createForm.value).subscribe(
        (response) => {
          console.log('Success:', response);
          this.createForm.reset();
          
        },
        (error) => {
          console.log('Error:', error);
        }
      );
    }
  }
}