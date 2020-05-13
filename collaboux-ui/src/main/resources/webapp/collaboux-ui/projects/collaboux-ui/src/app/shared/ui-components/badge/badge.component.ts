import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'badge',
  templateUrl: './badge.component.html',
  styleUrls: ['./badge.component.scss']
})
export class BadgeComponent implements OnInit{

  @Input()
  public imgSrc:string;

  @Input()
  public name:string;

  @Input()
  private value:string[] = [];

  @Output()
  public select:EventEmitter<any> = new EventEmitter<any>() ;

  @Input()
  public selected:boolean;

  constructor() { }

  ngOnInit(): void {
    this.selected = this.value && this.value.includes(this.name)
  }

  setSelected($event) {
    if(this.value.length < 5 || this.value.includes(this.name)){
      this.selected = !this.selected;
      this.select.emit(this);
      this.updateSelected();
    }

  }

  updateSelected(){
    if(!this.value.includes(this.name)){
      this.value.push(this.name)
    }else{
      this.value.splice(this.value.indexOf(this.name),1);
    }
  }
}
