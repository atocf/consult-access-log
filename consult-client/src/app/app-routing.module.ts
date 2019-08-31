import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FileLogCreateComponent } from './file-log-create/file-log-create.component';
import { FileLogListComponent } from './file-log-list/file-log-list.component';


const routes: Routes = [
  { path: '', redirectTo: 'fileLog', pathMatch: 'full' },
  { path: 'fileLogs', component: FileLogListComponent },
  { path: 'add', component: FileLogCreateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }