import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {IStompJS, StompInjectionToken} from "./shared/lib/stomp";

declare let SockJS;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'collaboux-ui';

  connected :Boolean;
  stompClient : IStompJS;

  constructor(@Inject(StompInjectionToken) private stomp: IStompJS, private http: HttpClient,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
   // either redirect user to there page or take them to a home page
    console.log(this.route.data)
    this.http.get("http://local.bloverse.com:8081/samples", {
      headers: new HttpHeaders(
        {"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg0NzA0MDE1LCJleHAiOjE1ODUwNjQwMTR9.eFsnqvA4DtqZk4gngm6lH92y6XPadLwRVKWVkRr-oP30kZSTjut4dOMcsIE_3KJlM90zg8qR71VXcQ95kVbkXg"}
      )
    }).subscribe(function (next) {
      console.log(next)
    });
  }

  doSomething():void{
    this.stompClient = this.stomp.over(new SockJS('http://localhost:8081/ws'));
    this.stompClient.connect({"access-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg0NzA0MDE1LCJleHAiOjE1ODUwNjQwMTR9.eFsnqvA4DtqZk4gngm6lH92y6XPadLwRVKWVkRr-oP30kZSTjut4dOMcsIE_3KJlM90zg8qR71VXcQ95kVbkXg"}, (frame) => {
      this.connected = true;
      this.stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log(JSON.parse(greeting.body).content);
      });

      this.stompClient.send("/app/hello", {}, JSON.stringify({"name": "Maison Armani"}));
    });

  }
}
