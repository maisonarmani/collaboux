import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ApplicationBootstrap} from "./shared/resolvers/application-bootstrap";
import {IndexComponent} from "./index/index.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


const routes: Routes = [
  {path: '',component: IndexComponent, resolve:{app_config:ApplicationBootstrap}},
  {path:'accounts',loadChildren:() => import('./accounts/accounts.module').then(m => m.AccountsModule)}
];

@NgModule({
  imports: [BrowserAnimationsModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
