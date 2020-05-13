import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {OptionsCardComponent} from "../../shared/ui-components/options-card/options-card.component";

@Component({
  selector: 'app-select-type',
  templateUrl: './select-type.component.html',
  styleUrls: ['./select-type.component.scss']
})
export class SelectTypeComponent {

  public selected:string;

  constructor(private activatedRoute :ActivatedRoute) {
    console.log(activatedRoute.snapshot)
  }

  setSelected($event: OptionsCardComponent) {
    this.selected = $event.name;
  }
}
