
import { NgModule } from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {SelectCardComponent} from "./ui-components/select-card/select-card.component";
import {OptionsCardComponent} from "./ui-components/options-card/options-card.component";
import {CommonModule, Location, LocationStrategy, PathLocationStrategy} from "@angular/common";
import { BadgeComponent } from './ui-components/badge/badge.component';
import {IconsModule} from "../icons/icons.module";
import {CacheableHttpClientService} from "./services/cacheable-http-client.service";
import { ModalComponent } from './ui-components/modal/modal.component';
import { CollabouxInputComponent } from './ui-components/collaboux-input/collaboux-input.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  imports: [
    CommonModule,
    IconsModule,
    ReactiveFormsModule
  ],
  declarations: [
    SelectCardComponent,OptionsCardComponent, BadgeComponent, ModalComponent, CollabouxInputComponent
  ],
  providers: [
    AuthenticationService, Location,CacheableHttpClientService, {provide: LocationStrategy, useClass: PathLocationStrategy}
  ],
  exports: [
    SelectCardComponent, OptionsCardComponent, BadgeComponent, ModalComponent, CollabouxInputComponent
  ]
})
export class SharedModule { }
