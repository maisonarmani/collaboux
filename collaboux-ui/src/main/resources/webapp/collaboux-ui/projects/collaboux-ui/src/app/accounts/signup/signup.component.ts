import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {


  @Output() changeView = new EventEmitter();

  constructor() { }

  login(){
    this.changeView.emit(true)
  }

  ngOnInit(): void {
  }


}
