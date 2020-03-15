import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {IEvent} from "./event.model";

@Injectable()
export class EventService{
  subject : Subject<IEvent[]>
  constructor(){
    this.subject = new Subject<IEvent[]>();
  }

  getAll(): Subject<IEvent[]> {
    return this.subject
  }
}


