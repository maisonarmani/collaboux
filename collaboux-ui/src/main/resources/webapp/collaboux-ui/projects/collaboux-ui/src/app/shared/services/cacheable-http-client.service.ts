import { Injectable } from '@angular/core';
import {HttpClient, HttpHandler, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CacheableHttpClientService extends HttpClient{

  constructor(handler: HttpHandler){
    super(handler);
  }

  cacheableGet(cacheName:string,url:string,options? : {headers:HttpHeaders,observe?:'body',params?: HttpParams,reportProgress?: boolean,
      responseType?:'json',withCredentials?: boolean}) : Observable<any>{
       return new Observable(subscriber => {
          super.get(url,options).subscribe(data=>{
            subscriber.next(data)
         });
      });
  }
}
