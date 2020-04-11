import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserComponent} from "./user.component";
import {EditComponent} from "./edit/edit.component";
import {UserGuard} from "./user.guard";
import {UserResolver} from "./user.resolver";


const routes: Routes = [
  {
    path:'', component:UserComponent, canActivate:[UserGuard], resolve:[UserResolver],
    children:[ { path:'edit', component:EditComponent}]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

