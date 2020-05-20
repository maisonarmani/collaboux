import { NgModule } from '@angular/core';
import { FeatherModule } from 'angular-feather';
import { Camera, Heart, Facebook,Twitch, User, Twitter,ChevronLeft, Check, X, MapPin} from 'angular-feather/icons';
import { SvgComponent } from './svg/svg.component';
import {CommonModule} from "@angular/common";
const icons = {
  Camera,
  Heart,
  User,
  Facebook,
  Twitch,
  Twitter,
  ChevronLeft,
  Check,X,MapPin
};

@NgModule({
  imports: [
    FeatherModule.pick(icons),CommonModule
  ],
  exports: [
    FeatherModule,
    SvgComponent
  ],
  declarations: [SvgComponent]
})
export class IconsModule { }
