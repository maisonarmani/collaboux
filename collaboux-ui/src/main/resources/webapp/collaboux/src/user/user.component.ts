import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IUser} from "../onboarding/shared/user.model";
import {of, pipe} from "rxjs";
import {filter, map} from "rxjs/operators";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  users : IUser[]
  constructor(private activatedRoute :ActivatedRoute) { }

  ngOnInit() {
    of(1,2,3,4,5).pipe(map((n)=>n*2)).subscribe(n=>console.log(n))
    this.users = this.activatedRoute.snapshot.data[0];
  }
}
