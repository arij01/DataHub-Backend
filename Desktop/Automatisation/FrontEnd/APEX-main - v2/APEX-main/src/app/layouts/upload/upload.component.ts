import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy } from '@angular/core';
import { NgModel } from '@angular/forms';
import { UploadService } from 'src/app/uploadService/upload.service';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [NgModel]
})
export class UploadComponent {
  selectedFile: File | null = null;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile) {
      console.log(`Selected File: ${this.selectedFile.name}`);
    }
  }

  uploadFiles(): void {
    const apiUrl = 'http://localhost:8083/Automation/upload';
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;

    if (this.selectedFile) {
        const selectedFile = this.selectedFile;

        const formData: FormData = new FormData();
        formData.append('file', selectedFile, selectedFile.name);

        this.http.post(apiUrl, formData, { responseType: 'text' })
            .subscribe(
                (response) => {
                    console.log('File uploaded successfully:', response);
                    alert('File uploaded successfully!');
                },
                (error) => {
                    console.error('Error uploading file:', error);
                    alert('Error uploading file. Please try again.');
                }
            );

        fileInput.value = '';
        this.selectedFile = null;
    } else {
        console.error('No file selected.');
        alert('Please select a file before uploading.');
    }
}
processFiles(): void {
  this.http.post('http://localhost:8083/Automation/processFiles', {})
    .subscribe(response => {
      console.log('Files processed successfully!', response);
    }, error => {
      console.error('Error processing files:', error);
    });
}
}
