import {Component} from '@angular/core';
import {Location} from "@angular/common";
import {NgSelectConfig} from "@ng-select/ng-select";


@Component({
  selector: 'setup',
  templateUrl: './setup.component.html'
})
export class SetupComponent {

  constructor(private location:Location, private config:NgSelectConfig) {
    this.config.notFoundText = 'Custom not found';
    this.config.appendTo = 'body';
    this.config.bindValue = 'value';
  }

  goBack(){
    this.location.back()
  }
}
