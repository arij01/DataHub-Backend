import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
import { UploadComponent } from './layouts/upload/upload.component';
import { GraphComponent } from './layouts/graph/graph.component';
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
    UploadComponent,
    GraphComponent
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
    MatFormFieldModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
