import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'icon-svg',
  templateUrl: './svg.component.html',
  styleUrls: ['./svg.component.scss']
})
export class SvgComponent implements OnInit {

  @Input()
  name:String;


  constructor() {

  }

  ngOnInit() {

  }

}
