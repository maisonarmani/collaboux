import {Component, Inject, OnInit} from '@angular/core';

import {StompInjectionToken, IStompJS} from "../../../collaboux-ui/src/app/shared/lib/stomp"

declare let SockJS:any;

@Component({
  selector: 'cbu-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'collaboux-components-ui';
  connected :Boolean;
  socket:any;
  stompClient : IStompJS;

  constructor(@Inject(StompInjectionToken) private stomp:IStompJS){

  }
  ngOnInit(): void {
    this.socket = new SockJS('http://localhost:8081/ws');
    this.stompClient = this.stomp.over(this.socket);
    this.stompClient.connect({"access-token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg0NzA0MDE1LCJleHAiOjE1ODUwNjQwMTR9.eFsnqvA4DtqZk4gngm6lH92y6XPadLwRVKWVkRr-oP30kZSTjut4dOMcsIE_3KJlM90zg8qR71VXcQ95kVbkXg"},  (frame)=> {
      this.connected = true;
      this.stompClient.subscribe('/topic/greetings',  (greeting) => {
        console.log(JSON.parse(greeting.body).content);
      });

      this.stompClient.send("/app/hello", {}, JSON.stringify({"name":"Maison Armani"}));
    })
  }

}
