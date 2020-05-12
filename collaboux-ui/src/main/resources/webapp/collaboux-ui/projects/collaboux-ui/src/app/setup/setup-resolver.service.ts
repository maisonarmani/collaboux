import { Injectable } from '@angular/core';
import {Resolve,ActivatedRouteSnapshot,RouterStateSnapshot} from "@angular/router";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SetupResolverService implements Resolve<any>{
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<String> {
    return of("string")
  }

  constructor() { }
}
