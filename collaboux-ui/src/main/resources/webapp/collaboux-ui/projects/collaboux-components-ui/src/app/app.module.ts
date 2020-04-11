import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { StompInjectionToken } from "../../../collaboux-ui/src/app/shared/lib/stomp"

declare let Stomp;
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [{
    provide:StompInjectionToken, useValue:Stomp
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
