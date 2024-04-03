import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { EnumToStringPipe } from './EnumToStringPipe ';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { HeaderComponent } from './layouts/header/header.component';
import { LoginComponent } from './layouts/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { GetAllColumnsComponent } from './layouts/get-all-columns/get-all-columns.component';
import { CreateColumnComponent } from './layouts/create-column/create-column.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EditColumnComponent } from './layouts/edit-column/edit-column.component';
import { EditDocComponent } from './layouts/edit-doc/edit-doc.component';
import { GetDocComponent } from './layouts/get-doc/get-doc.component';
import { ErrorComponent } from './layouts/error/error.component';
import { BreakLinesPipe } from './layouts/break-lines.pipe';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PageAjouterComponent } from './pages/page-ajouter/page-ajouter.component';
import { PageAfficherComponent } from './pages/page-afficher/page-afficher.component';
import { DatasetsComponent } from './components/datasets/datasets.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { GlossairesComponent } from './components/glossaires/glossaires.component';
import { ActionLogComponent } from './components/action-log/action-log.component';
import { UpdateDataSetComponent } from './components/update-data-set/update-data-set.component';
import { AddGlossaireComponent } from './components/add-glossaire/add-glossaire.component';
import { UpdateGlossairesComponent } from './components/update-glossaires/update-glossaires.component';
import { AddFeedbackComponent } from './components/add-feedback/add-feedback.component';
import { UpdateFeedbackComponent } from './components/update-feedback/update-feedback.component';
import { AddDatasetComponent } from './components/add-dataset/add-dataset.component';
import { DatePipe } from '@angular/common';
import { DataFlowDiagramComponent } from './components/data-flow-diagram/data-flow-diagram.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    LoginComponent,
    GetAllColumnsComponent,
    CreateColumnComponent,
    EditColumnComponent,
    EditDocComponent,
    GetDocComponent,
    ErrorComponent,
    BreakLinesPipe,
    PageAfficherComponent,
    PageAjouterComponent,
    DatasetsComponent,
    FeedbackComponent,
    GlossairesComponent,
    ActionLogComponent,
    UpdateDataSetComponent,
    AddGlossaireComponent,
    UpdateGlossairesComponent,
    AddFeedbackComponent,
    UpdateFeedbackComponent,
    AddDatasetComponent,
    EnumToStringPipe,
    DataFlowDiagramComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderComponent,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatFormFieldModule

  ],
  providers: [
    DatePipe, // Add DatePipe to the providers array
    // ... other providers
  ],  bootstrap: [AppComponent]
})
export class AppModule { }
