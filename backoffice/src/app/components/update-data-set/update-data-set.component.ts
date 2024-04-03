import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Datasets } from 'src/app/models/Datasets';
import { DatasetsService } from 'src/app/services/datasets.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-update-data-set',
  templateUrl: './update-data-set.component.html',
  styleUrls: ['./update-data-set.component.css']
})
export class UpdateDataSetComponent implements OnInit {
  aFormGroup!: FormGroup;
  dataset: Datasets = new Datasets();
  id!: number;
  imageFile: File | null = null;
  fileToUpload: File | null = null;

  constructor(
    private datasetsService: DatasetsService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe // Inject DatePipe
  ) {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
    });
  }

  ngOnInit() {
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });
    this.fetchDatasetDetails();

    this.setCurrentDate(); // Set the current date when component initializes
  }

  fetchDatasetDetails() {
    this.datasetsService.getDatasetById(this.id).subscribe({
      next: (data) => {
        this.dataset = data;
      },
      error: (error) => console.log(error),
      complete: () => console.log('done')
    });
  }

  setCurrentDate() {
    const currentDate = new Date();
    // Format the current date to 'yyyy-MM-dd'
    const formattedDate = this.datePipe.transform(currentDate, 'yyyy-MM-dd');

    if (formattedDate) {
      // Set the formatted date to the 'date d'ajout' field
      this.dataset.dateAjout = new Date(formattedDate);
    } else {
      console.error('formattedDate is null. Date could not be set.');
    }
  }

  update(form: NgForm) {
    if (form.valid) {
      if (this.id != undefined) {

       

        if (this.imageFile) {
          this.uploadImage(this.imageFile);
        }

        this.datasetsService.updateDataset(this.id, this.dataset).subscribe({
          next: () => {
            console.log("Dataset updated successfully!");
            this.router.navigate(['/datasets']);
          },
          error: (err) => console.log(err)
        });
      } else {
        console.error('Dataset not found');
      }
    }
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.fileToUpload = file;
  }

  uploadFile(file: File) {
    this.datasetsService.uploadFile(this.id, file).subscribe({
      next: () => {
        console.log("File uploaded successfully!");
      },
      error: (err: any) => console.log(err)
    });
  }

  onImageSelected(event: any) {
    const file: File = event.target.files[0];
    this.imageFile = file;
  }

  uploadImage(image: File) {
    this.datasetsService.uploadImage(this.id, image).subscribe({
      next: () => {
        console.log("Image uploaded successfully!");
      },
      error: (err: any) => console.log(err)
    });
  }

  siteKey: string = "6LeBnZUpAAAAAEDMtn5PQAEpTInPp0rB_fR60D-A";
}
