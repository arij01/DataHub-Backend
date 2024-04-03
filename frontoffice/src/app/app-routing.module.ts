import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './layouts/login/login.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { HeaderComponent } from './layouts/header/header.component';
import { EditColumnComponent } from './layouts/edit-column/edit-column.component';
import { GetAllColumnsComponent } from './layouts/get-all-columns/get-all-columns.component';
import { CreateColumnComponent } from './layouts/create-column/create-column.component';
import { GetDocComponent } from './layouts/get-doc/get-doc.component';
import { EditDocComponent } from './layouts/edit-doc/edit-doc.component';
import { ErrorComponent } from './layouts/error/error.component';
import { DatasetFrontComponent } from './components/dataset-front/dataset-front.component';
import { AddDatasetFrontComponent } from './components/add-dataset-front/add-dataset-front.component';
import { AddFeedbackFrontComponent } from './components/add-feedback-front/add-feedback-front.component';
import { AddGlossaireFrontComponent } from './components/add-glossaire-front/add-glossaire-front.component';
import { UpdateDatasetFrontComponent } from './components/update-dataset-front/update-dataset-front.component';
import { UpdateGlossaireFrontComponent } from './components/update-glossaire-front/update-glossaire-front.component';
import { UpdateFeedbackFrontComponent } from './components/update-feedback-front/update-feedback-front.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AboutUsPageComponent } from './components/about-us-page/about-us-page.component';

const routes: Routes = [
  { path: 'edit-column', component: EditColumnComponent },
  { path: 'get-all-columns', component: GetAllColumnsComponent },
  { path: 'create-column', component: CreateColumnComponent },
  { path: 'get-doc', component: GetDocComponent },
  { path: 'edit-doc', component: EditDocComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'datasets', component: DatasetFrontComponent },
  { path: 'addDataSet', component: AddDatasetFrontComponent },
  { path: 'addGlossaire', component: AddGlossaireFrontComponent },
  { path: 'addFeedback/:id', component: AddFeedbackFrontComponent },
  { path: 'updateDataSet/:id', component: UpdateDatasetFrontComponent },
  { path: 'updateGlossaire/:id', component: UpdateGlossaireFrontComponent },
  { path: 'updateFeedback/:id', component: UpdateFeedbackFrontComponent },
  { path: 'home', component: HomePageComponent },
  { path: 'aboutus', component: AboutUsPageComponent },




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
