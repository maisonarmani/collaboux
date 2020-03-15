import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class EventRouteActivator implements CanActivate{

  constructor(private router:Router){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean  {
    // route to login for start
    //this.router.navigate(['login']);
    return true;
  }

}
