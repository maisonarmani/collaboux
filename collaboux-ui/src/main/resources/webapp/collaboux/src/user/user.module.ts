import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import {UserRoutingModule} from "./user-routing.module";
import {UserService} from "../onboarding/shared/user.service";
import { EditComponent } from './edit/edit.component';



@NgModule({
  declarations: [UserComponent, EditComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ],providers:[
    UserService
  ]
})
export class UserModule { }
