import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { AccountsComponent } from './accounts.component';
import {AccountsRoutingModule} from "./accounts-routing.module";
import {FormsModule} from "@angular/forms";
import {IconsModule} from "../icons/icons.module";
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import {SharedModule} from "../shared/shared.module";

@NgModule({
  declarations: [SignupComponent, LoginComponent,AccountsComponent, ForgetPasswordComponent],
  imports: [
    CommonModule, AccountsRoutingModule, FormsModule, IconsModule,SharedModule
  ]
})
export class AccountsModule { }
