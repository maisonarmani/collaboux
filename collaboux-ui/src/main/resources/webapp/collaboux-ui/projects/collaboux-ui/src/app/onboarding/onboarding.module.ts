import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { SetupComponent } from './setup/setup.component';
import {OnboardingRoutingModule} from "./onboarding-routing.module";



@NgModule({
  declarations: [SignupComponent, LoginComponent, SetupComponent],
  imports: [
    CommonModule,OnboardingRoutingModule
  ]
})
export class OnboardingModule { }
