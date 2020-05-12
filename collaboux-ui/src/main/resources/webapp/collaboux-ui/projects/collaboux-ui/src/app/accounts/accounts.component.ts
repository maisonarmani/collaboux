import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {
  isLogin : boolean  = true
  isRegister : boolean = false
  constructor() { }

  ngOnInit() {
  }

}
