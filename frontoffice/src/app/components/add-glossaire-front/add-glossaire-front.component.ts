import { Component } from '@angular/core';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Datasets } from 'src/app/models/Datasets';
import { Glossaires } from 'src/app/models/Glossaires';
import { DatasetsService } from 'src/app/services/datasets.service';
import { GlossairesService } from 'src/app/services/glossaires.service';

@Component({
  selector: 'app-add-glossaire-front',
  templateUrl: './add-glossaire-front.component.html',
  styleUrls: ['./add-glossaire-front.component.css']
})
export class AddGlossaireFrontComponent {

  aFormGroup!: FormGroup;
  glossaire: Glossaires = new Glossaires();
  formSubmitted: boolean = false;
  datasets: Datasets[] = [];

  constructor(
    private glossairesService: GlossairesService,
    private router: Router,
    private formBuilder: FormBuilder,
    private datasetService: DatasetsService
  ) {}

  ngOnInit() {
    this.loadDatasets();
  }

  loadDatasets() {
    this.datasetService.getAllDatasets().subscribe(
      datasets => {
        this.datasets = datasets;
      },
      error => {
        console.log('Erreur lors de la récupération des datasets:', error);
      }
    );
  }

  add(f: NgForm) {
    if (f.valid) {
      if (this.glossaire.dataset && this.glossaire.dataset.id) {
        const selectedDatasetId = this.glossaire.dataset.id;
        console.log("Selected Dataset ID:", selectedDatasetId);
        this.glossairesService.addGlossaires(this.glossaire, selectedDatasetId).subscribe({
          next: () => {
            console.log("Glossaire added successfully!");
          },
          error: (err: any) => console.log(err)
        });
      } else {
        console.log("Dataset is not selected or does not have an ID.");
      }
    } else {
      console.log("Le formulaire est invalide.");
    }
  }
}

