import { Injectable } from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpRequest,HttpHandler} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NoopInterceptorService implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("dkpokopk",req)
    return next.handle(req);
  }
}
