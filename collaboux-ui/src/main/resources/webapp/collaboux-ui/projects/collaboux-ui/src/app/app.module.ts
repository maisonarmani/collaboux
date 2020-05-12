import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgSelectModule } from '@ng-select/ng-select';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexModule } from './index/index.module';
import {CollabouxComponentsComponent} from "collaboux-components"
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NoopInterceptorService} from "./shared/services/noop-interceptor.service";
import {AccountsModule} from "./accounts/accounts.module";
import {StompInjectionToken} from "./shared/lib/stomp"
import {ApplicationBootstrap} from "./shared/resolvers/application-bootstrap";
import {SharedModule} from "./shared/shared.module";

declare let Stomp;

@NgModule({
  declarations: [
    AppComponent, CollabouxComponentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AccountsModule,
    IndexModule,
    SharedModule,
    NgSelectModule
  ],
  providers: [
    ApplicationBootstrap,
    {provide: StompInjectionToken, useValue: Stomp},
    {provide: HTTP_INTERCEPTORS, multi: true, useClass: NoopInterceptorService}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
