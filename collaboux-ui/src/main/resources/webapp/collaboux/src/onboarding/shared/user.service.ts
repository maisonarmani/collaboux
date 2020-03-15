import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {IEvent} from "../../shared/event.model";
import {IUser} from "./user.model";

@Injectable()
export class UserService{
  subject : Subject<IUser[]>

  constructor(){
    this.subject = new Subject<IUser[]>();
  }

  getUsers(): Subject<IUser[]> {
    setTimeout(()=> {
       this.subject.next([{
         "username":"Maison Armani",
         "authorities":["USER","ADMIN"]
       }]);

      this.subject.complete()
    },5000);

    return this.subject
  }
}


