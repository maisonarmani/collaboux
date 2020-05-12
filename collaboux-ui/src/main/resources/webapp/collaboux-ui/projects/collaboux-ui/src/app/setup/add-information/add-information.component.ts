import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-add-information',
  templateUrl: './add-information.component.html',
  styleUrls: ['./add-information.component.scss']
})
export class AddInformationComponent implements OnInit {

  @Input()
  private badgesSelected: string[] = ["Singer"];

  constructor() { }

  ngOnInit() {

  }

}
