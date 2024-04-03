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
import { ActionLogComponent } from './components/action-log/action-log.component';
import { DatasetsComponent } from './components/datasets/datasets.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { GlossairesComponent } from './components/glossaires/glossaires.component';
import { AddDatasetComponent } from './components/add-dataset/add-dataset.component';
import { AddGlossaireComponent } from './components/add-glossaire/add-glossaire.component';
import { AddFeedbackComponent } from './components/add-feedback/add-feedback.component';
import { UpdateFeedbackComponent } from './components/update-feedback/update-feedback.component';
import { UpdateGlossairesComponent } from './components/update-glossaires/update-glossaires.component';
import { UpdateDataSetComponent } from './components/update-data-set/update-data-set.component';
import { DataFlowDiagramComponent } from './components/data-flow-diagram/data-flow-diagram.component';



const routes: Routes = [
  { path: 'edit-column', component: EditColumnComponent },
  { path: 'get-all-columns', component: GetAllColumnsComponent },
  { path: 'create-column', component: CreateColumnComponent },
  { path: 'get-doc', component: GetDocComponent },
  { path: 'edit-doc', component: EditDocComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'actionlog', component: ActionLogComponent },
  { path: 'datasets', component: DatasetsComponent },
  { path: 'feedbacks', component: FeedbackComponent },
  { path: 'glossaires', component: GlossairesComponent },
  { path: 'addDataSet', component: AddDatasetComponent },
  { path: 'addGlossaire', component: AddGlossaireComponent },
  { path: 'addFeedback/:id', component: AddFeedbackComponent },
  { path: 'updateDataSet/:id', component: UpdateDataSetComponent },
  { path: 'updateGlossaire/:id', component: UpdateGlossairesComponent },
  { path: 'updateFeedback/:id', component: UpdateFeedbackComponent },
  { path: 'diag', component: DataFlowDiagramComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
