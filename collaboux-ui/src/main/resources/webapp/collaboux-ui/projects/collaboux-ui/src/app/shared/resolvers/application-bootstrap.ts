import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable, of} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({providedIn:"root"})
export class ApplicationBootstrap implements Resolve<String>{
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<String> {
    return of("Maison");
  }

}
