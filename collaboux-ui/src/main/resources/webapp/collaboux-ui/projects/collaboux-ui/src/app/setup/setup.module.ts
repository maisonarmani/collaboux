import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SelectTypeComponent } from './select-type/select-type.component';
import { AddInformationComponent } from './add-information/add-information.component';
import { CompletionComponent } from './completion/completion.component';
import {IconsModule} from "../icons/icons.module";
import {SetupRoutingModule} from "./setup-routing.module";
import {SetupComponent} from "./setup.component";
import {SharedModule} from "../shared/shared.module";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [SelectTypeComponent,SetupComponent, AddInformationComponent, CompletionComponent],
  imports: [
    CommonModule, IconsModule, SetupRoutingModule, SharedModule, NgSelectModule, FormsModule, ReactiveFormsModule
  ]
})
export class SetupModule { }
