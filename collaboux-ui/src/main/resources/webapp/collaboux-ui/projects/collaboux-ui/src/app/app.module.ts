import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {CollabouxComponentsComponent} from "collaboux-components"
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NoopInterceptorService} from "./shared/noop-interceptor.service";
import {OnboardingModule} from "./onboarding/onboarding.module";
import {StompInjectionToken} from "./shared/lib/stomp"
import {ApplicationBootstrap} from "./shared/resolvers/application-bootstrap";

declare let Stomp;

@NgModule({
  declarations: [
    AppComponent,CollabouxComponentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    OnboardingModule
  ],
  providers: [
    ApplicationBootstrap,
    {provide:StompInjectionToken, useValue:Stomp},
    {provide:HTTP_INTERCEPTORS, multi:true, useClass:NoopInterceptorService}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
