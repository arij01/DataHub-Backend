import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy } from '@angular/core';
import { NgModel } from '@angular/forms';
import { NotificationService } from 'src/app/NotificationService/notification.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [NgModel]
})
export class UploadComponent {
  selectedFile: File | null = null;

  constructor(private http: HttpClient, private notificationService: NotificationService, private toastr: ToastrService) {}

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
          this.toastr.success('File uploaded successfully!', 'Success', {
            toastClass: 'custom-toast-success',
            positionClass: 'toast-bottom-right',
            timeOut: 3000,
            progressBar: true,
            progressAnimation: 'increasing',
            tapToDismiss: false
          });
        },
        (error) => {
          console.error('Error uploading file:', error);
          this.toastr.error('Error uploading file. Please try again.', 'Error', {
            toastClass: 'custom-toast-success',
            positionClass: 'toast-bottom-right',
            timeOut: 3000,
            progressBar: true,
            progressAnimation: 'increasing',
            tapToDismiss: false
          });
        }
      );

    fileInput.value = '';
    this.selectedFile = null;
  } else {
    console.error('No file selected.');
    this.toastr.error('Please select a file before uploading.', 'Error', {
      toastClass: 'custom-toast-success',
      positionClass: 'toast-bottom-right',
      timeOut: 3000,
      progressBar: true,
      progressAnimation: 'increasing',
      tapToDismiss: false
    });
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
