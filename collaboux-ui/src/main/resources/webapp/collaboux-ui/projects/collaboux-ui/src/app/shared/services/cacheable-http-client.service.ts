import { Injectable } from '@angular/core';
import {HttpClient, HttpHandler, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {LocalStorage} from "@ngx-pwa/local-storage"

@Injectable({
  providedIn: 'root'
})
export class CacheableHttpClientService extends HttpClient{

  constructor(handler: HttpHandler, private localStorage:LocalStorage){
    super(handler);
  }

  cacheableGet(cacheName:string,url:string,options? : {headers:HttpHeaders,observe?:'body',params?: HttpParams,reportProgress?: boolean,
      responseType?:'json',withCredentials?: boolean}) : Observable<any>{
       return new Observable(subscriber => {
         this.localStorage.getItem(cacheName).subscribe(data=>{
           if(data != null){
             subscriber.next(data)
           }else{
             super.get(url,options).subscribe(data=>{
               this.localStorage.setItem(cacheName, data).subscribe();
               subscriber.next(data)
             });
           }
         });
      });
  }
}
