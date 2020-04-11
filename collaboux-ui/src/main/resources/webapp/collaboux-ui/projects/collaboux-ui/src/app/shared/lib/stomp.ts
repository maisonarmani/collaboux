import {InjectionToken} from "@angular/core";

export interface IStompJS {
  subscribe(topic:String, callback:Function);
  over(socket:any) : IStompJS;
  connect(header:any,callback:Function )
  send(endpoint:string,auth:any, payload:string )
}
export let StompInjectionToken = new InjectionToken<IStompJS>("StompInjectionToken")
