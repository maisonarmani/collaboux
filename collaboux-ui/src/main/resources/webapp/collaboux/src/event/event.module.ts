import { BrowserModule } from '@angular/platform-browser';
import {InjectionToken, NgModule} from '@angular/core';

import { EventRoutingModule } from './event-routing.module';
import { EventComponent } from './event.component';
import { EventListComponent } from './list/event-list.component';
import {EventService} from "../shared/event.service";
import {ClosurePipe} from "../shared/closure.pipe";
import { HttpClientModule} from "@angular/common/http";
import {UserService} from "../onboarding/shared/user.service";

@NgModule({
  declarations: [
    EventComponent,
    EventListComponent,
    ClosurePipe
  ],
  imports: [
    BrowserModule,
    EventRoutingModule,
    HttpClientModule
  ],
  providers: [EventService,UserService,{provide:'logger', useValue:function(value){
    console.log(value)
  }}],
  bootstrap: [EventComponent]
})
export class EventModule { }
