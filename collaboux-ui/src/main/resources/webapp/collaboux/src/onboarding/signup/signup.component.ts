import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  auth = new FormGroup({
    username :new FormControl('username'),
    password : new FormControl('password'),
    email : new FormControl('email'),
  })
  constructor() {

  }

  ngOnInit() {

  }

  signUp(){
      console.log(this.auth)
  }

}
