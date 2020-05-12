
import { NgModule } from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {SelectCardComponent} from "./ui-components/select-card/select-card.component";
import {OptionsCardComponent} from "./ui-components/options-card/options-card.component";
import {CommonModule, Location, LocationStrategy, PathLocationStrategy} from "@angular/common";
import { BadgeComponent } from './ui-components/badge/badge.component';
import {IconsModule} from "../icons/icons.module";


@NgModule({
  imports: [
    CommonModule,
    IconsModule
  ],
  declarations: [
    SelectCardComponent,OptionsCardComponent, BadgeComponent
  ],
  providers: [
    AuthenticationService, Location, {provide: LocationStrategy, useClass: PathLocationStrategy}
  ],
  exports: [
    SelectCardComponent, OptionsCardComponent, BadgeComponent
  ]
})
export class SharedModule { }
