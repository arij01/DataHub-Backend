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
import { UploadComponent } from './layouts/upload/upload.component';
import { GraphComponent } from './layouts/graph/graph.component';

const routes: Routes = [
{
  path: 'login',
  component: LoginComponent,
  
},
{
  path: 'side',
  component: SidebarComponent,
  
},
{
  path: 'header',
  component: HeaderComponent,
  
},

  { path: 'columns', component: GetAllColumnsComponent },
  { path: 'columns/edit/:id', component: EditColumnComponent },
  { path: 'columns/create', component: CreateColumnComponent },
  { path: '', redirectTo: '/columns', pathMatch: 'full' },
  { path: 'documentations/get/:id', component: GetDocComponent },
  { path: 'documentations/update/:id', component: EditDocComponent },
  {path: 'upload',component: UploadComponent},
  {path: 'Quality',component: GraphComponent},
  { path: 'error', component: ErrorComponent },
  { path: '**', redirectTo: '/error' }
   

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
