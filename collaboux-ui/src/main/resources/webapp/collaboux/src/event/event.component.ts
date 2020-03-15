import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {EventService} from "../shared/event.service";
import {Subject} from "rxjs";
import {IEvent} from "../shared/event.model";


@Component({
  selector: 'event-app',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit{
  eventSubject: Subject<IEvent[]>

  @Input()
  type : String;

  @Output()
  event: EventEmitter<String>;

  constructor(private eventService : EventService, @Inject('logger') logger:any){
    this.event = new EventEmitter();
  }

  ngOnInit(): void {
    this.event.subscribe((evet)=>{
      console.log(evet)
    })

    setTimeout(()=>{
      this.event.emit("Maison")
    },3000)
  }
}
