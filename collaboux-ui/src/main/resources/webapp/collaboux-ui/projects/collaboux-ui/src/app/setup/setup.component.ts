import {Component} from '@angular/core';
import {Location} from "@angular/common";


@Component({
  selector: 'setup',
  templateUrl: './setup.component.html'
})
export class SetupComponent {

  constructor(private location:Location) {
  }

  goBack(){
    this.location.back()
  }
}
