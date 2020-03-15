import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {OnboardingRoutingModule} from "./onboarding-routing.module";
import { SignupComponent } from './signup/signup.component';



@NgModule({
  declarations: [LoginComponent, SignupComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,FormsModule,
    OnboardingRoutingModule
  ],
  providers:[{
    provide:'localStorage', useValue:localStorage
  }]
})
export class OnboardingModule { }
