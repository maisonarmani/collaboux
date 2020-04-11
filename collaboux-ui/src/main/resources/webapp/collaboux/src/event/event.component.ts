import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {EventService} from "../shared/event.service";
import {Subject} from "rxjs";
import {IEvent} from "../shared/event.model";
import {NavigationEnd, NavigationStart, Router, RouterEvent} from "@angular/router";


@Component({
  selector: 'event-app',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit{
  eventSubject: Subject<IEvent[]>;

  @Input()
  type : String;

  @Output()
  event: EventEmitter<String>;

  loading: boolean;
  constructor(private router: Router,private eventService : EventService, @Inject('logger') logger:any) {
    this.loading = false;
    this.event = new EventEmitter();
    router.events.subscribe(
      (event: RouterEvent): void => {
        if (event instanceof NavigationStart) {
          this.loading = true;
        } else if (event instanceof NavigationEnd) {
          this.loading = false;
        }
      }
    );
  }
  ngOnInit(): void {
    this.event.subscribe((evet)=>{
      console.log(evet)
    })

    setTimeout(()=>{
      this.event.emit("Maison")
    },3000)

    this.router.navigate(['/auth/login']);
  }
}
