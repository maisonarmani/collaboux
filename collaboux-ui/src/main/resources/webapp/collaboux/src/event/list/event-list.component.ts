import {Component, EventEmitter, Output} from '@angular/core';
import {EventService} from "../../shared/event.service";
import {ActivatedRoute, ActivatedRouteSnapshot} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'event-list-app',
  templateUrl: './event-list.component.html'
})
export class EventListComponent{
  @Output()
  event: EventEmitter<String>;

  constructor(private eventService : EventService, private activatedRoute : ActivatedRoute, private http:HttpClient){
    console.log(activatedRoute.snapshot.queryParams.token)
    http.get("http://local.collaboux.com:8080/user/me",{
      headers:new HttpHeaders(
        {"Authorization":"Bearer "+activatedRoute.snapshot.queryParams.token}
      )
    }).subscribe(function (next) {
      console.log(next)
    });
    this.event = new EventEmitter();

  }

}
