import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DocService } from 'src/app/DocServices/doc.service';
import { Documentation } from 'src/app/model/documentation';

@Component({
  selector: 'app-get-doc',
  templateUrl: './get-doc.component.html',
  styleUrls: ['./get-doc.component.css']
})
export class GetDocComponent implements OnInit, OnDestroy {
  doc: Documentation | undefined;
  id: string | null | undefined;
  private unsubscribe$: Subject<void> = new Subject<void>();

  constructor(
    private httpService: DocService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.httpService.getDocumentationById(this.id)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(
          documentation => {
            this.doc = documentation;
            console.log('Doc fetched:', this.doc);
          },
          error => {
            console.error('Error fetching doc:', error);
            // Handle the error accordingly, e.g., show a message to the user
            this.router.navigate(['/columns']);
          }
        );
    } else {
      this.router.navigate(['/columns']);
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
