import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Output() changeView = new EventEmitter();

  constructor() { }

  signUp(){
    this.changeView.emit(true)
  }

  ngOnInit() {

  }
}
