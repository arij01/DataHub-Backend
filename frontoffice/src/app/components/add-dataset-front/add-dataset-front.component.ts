import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Datasets } from 'src/app/models/Datasets'; 
import { DatasetsService } from 'src/app/services/datasets.service';

@Component({
  selector: 'app-add-dataset-front',
  templateUrl: './add-dataset-front.component.html',
  styleUrls: ['./add-dataset-front.component.css']
})
export class AddDatasetFrontComponent {
  aFormGroup!: FormGroup;
  dataset: Datasets = new Datasets();
  formSubmitted: boolean = false;

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
    console.log(this.dataset);
    this.datasetsService.addDataset(this.dataset).subscribe({
      next: () => {
        console.log("Task added successfully!");
        this.router.navigate(['/datasets']);
      },
      error: (err: any) => console.log(err)
    });
  }

  siteKey: string = "6LeBnZUpAAAAAEDMtn5PQAEpTInPp0rB_fR60D-A";
}
