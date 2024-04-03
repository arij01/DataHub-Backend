import { Component, OnInit } from '@angular/core';

import { Datasets } from 'src/app/models/Datasets';
import { DatasetsService } from 'src/app/services/datasets.service';
import { DatePipe } from '@angular/common';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-dataset',
  templateUrl: './add-dataset.component.html',
  styleUrls: ['./add-dataset.component.css']
})
export class AddDatasetComponent implements OnInit {
  aFormGroup!: FormGroup;
  dataset: Datasets = new Datasets();
  formSubmitted: boolean = false;
  selectedImage: File | null = null;
  selectedFile: File | null = null;

  constructor(
    private datasetsService: DatasetsService,
    private router: Router,
    private formBuilder: FormBuilder,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });

    this.setCurrentDate();
  }

  onImageSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }

  onCVSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  setCurrentDate() {
    const currentDate = new Date();
    const formattedDate = this.datePipe.transform(currentDate, 'yyyy-MM-dd');

    if (formattedDate) {
      this.dataset.dateAjout = new Date(formattedDate);
    } else {
      console.error('formattedDate is null. Date could not be set.');
    }
  }

  add(f: NgForm) {
    if (f.invalid) {
      this.formSubmitted = true;
      return;
    }

    this.datasetsService.addDataset(this.dataset).subscribe({
      next: (createdDataset: Datasets) => {
        const id = createdDataset.id;

        if (this.selectedImage) {
          this.uploadImage(id);
        }

        console.log("Dataset added successfully!", createdDataset);
        this.dataset.id = createdDataset.id;
        if (this.selectedFile) {
          this.uploadImage(id);
        }
        this.router.navigate(['/datasets']);
      },
      error: (err: any) => console.log(err)
    });
  }

  uploadFile(id: number) {
    if (this.selectedFile) {
      this.datasetsService.uploadFile(id, this.selectedFile).subscribe(
        (response: any) => {
          console.log('File uploaded successfully: ', response);
        },
        (error) => {
          console.error('Error uploading file: ', error);
        }
      );
    }
  }

  uploadImage(id: number) {
    if (this.selectedImage) {
      this.datasetsService.uploadImage(id, this.selectedImage).subscribe(
        (response: any) => {
          console.log('Image uploaded successfully: ', response);
        },
        (error) => {
          console.error('Error uploading image: ', error);
        }
      );
    }
  }

  siteKey: string = "6LeBnZUpAAAAAEDMtn5PQAEpTInPp0rB_fR60D-A";
}
