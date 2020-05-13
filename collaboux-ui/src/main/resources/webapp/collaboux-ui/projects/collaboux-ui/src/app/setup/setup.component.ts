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
    // set the bindValue to global config when you use the same
    // bindValue in most of the place.
    // You can also override bindValue for the specified template
    // by defining `bindValue` as property
    // Eg : <ng-select bindValue="some-new-value"></ng-select>
    this.config.bindValue = 'value';
  }

  goBack(){
    this.location.back()
  }
}
