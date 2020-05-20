import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexModule } from './index/index.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NoopInterceptorService} from "./shared/services/noop-interceptor.service";
import {AccountsModule} from "./accounts/accounts.module";
import {StompInjectionToken} from "./shared/lib/stomp"
import {ApplicationBootstrap} from "./shared/resolvers/application-bootstrap";
import {SharedModule} from "./shared/shared.module";

declare let Stomp;
const  StompProvider = {provide: StompInjectionToken, useValue: Stomp};
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AccountsModule,
    IndexModule,
    SharedModule
  ],
  providers: [
    ApplicationBootstrap, StompProvider,
    {provide: HTTP_INTERCEPTORS, multi: true, useClass: NoopInterceptorService}
  ],
  entryComponents:[],
  bootstrap: [AppComponent]
})
export class AppModule { }
