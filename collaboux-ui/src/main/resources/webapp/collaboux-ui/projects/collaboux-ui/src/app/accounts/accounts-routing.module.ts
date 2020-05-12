import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SignupComponent} from "./signup/signup.component";
import {LoginComponent} from "./login/login.component";
import {ForgetPasswordComponent} from "./forget-password/forget-password.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


const routes: Routes = [
  {
    path:'',
    children:[
      { path:'', component:LoginComponent },
      { path:'sign-up', component:SignupComponent },
      { path:'sign-in', component:LoginComponent },
      { path:'forgot-password', component:ForgetPasswordComponent },
      { path:'setup',loadChildren:() => import('../setup/setup.module').then(m => m.SetupModule) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountsRoutingModule { }
