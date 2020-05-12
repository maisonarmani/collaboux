import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index.component';
import {IndexRoutingModule} from "./index-routing.module";
import {IconsModule} from "../icons/icons.module";

@NgModule({
  declarations: [IndexComponent],
  imports: [
    CommonModule,IndexRoutingModule,IconsModule
  ]
})
export class IndexModule { }
