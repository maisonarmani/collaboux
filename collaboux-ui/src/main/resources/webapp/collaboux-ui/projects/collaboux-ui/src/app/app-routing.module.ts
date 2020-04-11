import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginComponent} from "./onboarding/login/login.component";
import {ApplicationBootstrap} from "./shared/resolvers/application-bootstrap";


const routes: Routes = [
  {path: '',component: AppComponent, resolve:{app_config:ApplicationBootstrap}},
  {path:'auth',loadChildren:'./onboarding/onboarding.module#OnboardingModule', component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
