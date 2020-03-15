import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  auth = new FormGroup({
    username:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required])
  })

  constructor(private http:HttpClient, private router : Router) {

  }

  ngOnInit() {
    this.auth.patchValue({
      username:'masonarmani38@gmail.com',
      password:'collaboux',
    })
  }

  login(){
    if(this.auth.status == 'VALID'){
      this.http.post("http://local.collaboux.com:8080/auth/login",{
        email:this.auth.value.username,
        password:this.auth.value.password
      }).subscribe((result)=>{
        // what ever error is returned will be displayed to the user
        if (result['accessToken']){
          localStorage.setItem("access_token",result['accessToken']);
          this.router.navigate(["user"]);
        }
      })
    }
  }
}
