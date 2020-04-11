import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class EventRouteActivator implements CanActivate{

  constructor(private router:Router){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean  {
    return true;
  }

}
