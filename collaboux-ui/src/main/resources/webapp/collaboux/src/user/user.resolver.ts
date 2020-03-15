import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Resolve, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {UserService} from "../onboarding/shared/user.service";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserResolver implements Resolve<any> {
  constructor(private userService:UserService){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    return this.userService.getUsers().pipe(map(users=>users));
  }

}
