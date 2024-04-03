import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { DocService } from 'src/app/DocServices/doc.service';
import { Documentation } from 'src/app/model/documentation';



@Component({
  selector: 'app-edit-doc',
  templateUrl: './edit-doc.component.html',
  styleUrls: ['./edit-doc.component.css']
})
export class EditDocComponent implements OnInit {
  documentation!: Documentation;
  minDate = new Date(1900, 0, 1);
  maxDate = new Date();
  editForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private documentationService: DocService
  ) {
    this.editForm = this.formBuilder.group({
      title: ['', Validators.required],
      createdAt: [''],
      lastUpdated: [''],
      text: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.documentationService.getDocumentationById(id).subscribe(
        (documentation) => {
          console.log('Fetched documentation:', documentation);
          this.documentation = documentation;
          this.editForm.patchValue({
            title: documentation.title,
            createdAt: documentation.createdAt,
            lastUpdated: documentation.lastUpdated,
            text: documentation.text
          });
        },
        (error) => {
          console.error('Error fetching documentation:', error);
        }
      );
    }
  }

  handelSubmitUpdate(value: Documentation): void {
    // Update the documentation object with the new values
    this.documentation.title = value.title;
    this.documentation.createdAt = value.createdAt;
    this.documentation.lastUpdated = value.lastUpdated;
    this.documentation.text = value.text;

    // Call the service method to update the documentation
    this.documentationService.updateDocById(this.documentation.id,this.documentation).subscribe(
      () => {
        console.log('Documentation updated successfully');
        // Navigate to the detail page after successful update
        this.router.navigate(['/documentations', this.documentation.id]);
      },
      (error) => {
        console.error('Error updating documentation:', error);
      }
    );
  }
}