import { Component, ElementRef, OnInit } from '@angular/core';
import { Datasets } from 'src/app/models/Datasets';
import { DatasetsService } from 'src/app/services/datasets.service';
import { FileResponse } from 'src/app/models/FileResponse';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { Router } from '@angular/router';
import * as d3 from 'd3';

@Component({
  selector: 'app-datasets',
  templateUrl: './datasets.component.html',
  styleUrls: ['./datasets.component.css']
})
export class DatasetsComponent implements OnInit {
  datasets: Datasets[] = [];
  imageFiles: FileResponse[] = [];
  otherFiles: FileResponse[] = [];
  selectedFile: File | null = null;

  constructor(
    private datasetsService: DatasetsService,
    private http: HttpClient,
    private router: Router,
    private readonly elementRef: ElementRef
  ) { }

  addFeedback(datasetId: number, datasetName: string) {
    this.router.navigate(['/addFeedback', datasetId], { state: { datasetName: datasetName } });
  }

  ngOnInit(): void {
    this.loadFiles();
    this.fetchDatasets();
  }

  isImage(fileName: string): boolean {
    return !!fileName && (fileName.endsWith('.png') || fileName.endsWith('.jpg') || fileName.endsWith('.avif') || fileName.endsWith('.svg'));
  }

  loadFiles() {
    this.http.get<FileResponse[]>('http://localhost:8082/demo/datasets/all').subscribe(
      (response) => {
        this.imageFiles = response.filter(file => this.isImage(file.fileName));
        this.otherFiles = response.filter(file => !this.isImage(file.fileName));
      },
      (error) => {
        console.error('Erreur lors du chargement des fichiers : ', error);
      }
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this.http.post('http://localhost:8082/demo/datasets/{id}/file', formData).subscribe(
        (response: any) => {
          console.log('File added successfully: ', response);
          this.selectedFile = null;
          this.loadFiles();
        },
        (error) => {
          console.error('Error adding file: ', error);
        }
      );
    } else {
      console.error('No file selected.');
    }
  }

  uploadImage() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this.http.post('http://localhost:8082/demo/datasets/{id}/image', formData).subscribe(
        (response: any) => {
          console.log('image added successfully: ', response);
          this.selectedFile = null;
          this.loadFiles();
        },
        (error) => {
          console.error('Error adding file: ', error);
        }
      );
    } else {
      console.error('No file selected.');
    }
  }

  openImage(filename: string) {
    const headers = new HttpHeaders().set('Accept', 'application/avif');
    this.http.get('http://localhost:8082/demo/datasets/{id}/image', { headers, responseType: 'blob' })
      .subscribe(
        (blob) => {
          const file = new Blob([blob], { type: 'application/avif' });
          saveAs(file, filename);
        },
        (error) => {
          console.error('Erreur lors de l\'ouverture du fichier : ', error);
        }
      );
  }

  deleteDataset(id: number): void {
    this.datasetsService.deleteDataset(id).subscribe({
      next: () => {
        console.log('Dataset deleted successfully!');
        this.fetchDatasets();
      },
      error: (err: any) => {
        console.error('Error deleting Dataset:', err);
      }
    });
  }

  updateDataset(dataset: any) {
    this.router.navigate(['/updateDataSet', dataset]);
    console.log('Updating dataset:', dataset);
  }

  fetchDatasets() {
    this.datasetsService.getAllDatasets().subscribe({
      next: (data) => {
        this.datasets = data;
      },
      error: (error) => console.log(error),
      complete: () => console.log('done')
    });
  }


}
