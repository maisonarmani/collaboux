import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'options-card',
  templateUrl: './options-card.component.html',
  styleUrls: ['./options-card.component.scss']
})
export class OptionsCardComponent implements OnInit {

  @Input()
  private imgSrc:string;

  @Input()
  public name:string;

  @Input()
  public selected : boolean = false;

  @Input()
  private title:string;

  @Output()
  private select =  new EventEmitter<Object>()


  constructor() { }

  setSelect($event){
    this.selected = !this.selected;
    this.select.emit(this)
  }

  ngOnInit() {

  }

}
