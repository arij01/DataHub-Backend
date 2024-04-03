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
import { DatasetFrontComponent } from './components/dataset-front/dataset-front.component';
import { AddFeedbackFrontComponent } from './components/add-feedback-front/add-feedback-front.component';
import { UpdateDatasetFrontComponent } from './components/update-dataset-front/update-dataset-front.component';
import { AddDatasetFrontComponent } from './components/add-dataset-front/add-dataset-front.component';
import { DatePipe } from '@angular/common';
import { UpdateGlossaireFrontComponent } from './components/update-glossaire-front/update-glossaire-front.component';
import { UpdateFeedbackFrontComponent } from './components/update-feedback-front/update-feedback-front.component';
import { AddGlossaireFrontComponent } from './components/add-glossaire-front/add-glossaire-front.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AboutUsPageComponent } from './components/about-us-page/about-us-page.component';

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
    EnumToStringPipe,
    DatasetFrontComponent,
    AddFeedbackFrontComponent,
    UpdateDatasetFrontComponent,
    AddDatasetFrontComponent,
    UpdateGlossaireFrontComponent,
    UpdateFeedbackFrontComponent,
   DatasetFrontComponent,
   AddGlossaireFrontComponent,
   HomePageComponent,
   AboutUsPageComponent,
   
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
