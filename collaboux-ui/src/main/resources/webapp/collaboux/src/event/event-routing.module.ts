import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {EventListComponent} from "./list/event-list.component";
import {EventRouteActivator} from "../shared/event.route.activator";
import {UserGuard} from "../user/user.guard";
import {UserResolver} from "../user/user.resolver";



const routes: Routes = [
  {path:'',loadChildren:'../onboarding/onboarding.module#OnboardingModule', pathMatch:'full'},
  {path:'event', component:EventListComponent, canActivate:[EventRouteActivator]},
  {path:'event-list', component:EventListComponent, canActivate:[EventRouteActivator]},
  {path:'login',loadChildren:'../onboarding/onboarding.module#OnboardingModule', pathMatch:'full'},
  {path:'user',loadChildren:'../user/user.module#UserModule', canActivate:[UserGuard], resolve:[UserResolver], pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)] ,
  exports: [RouterModule],
  providers:[EventRouteActivator]
})
export class EventRoutingModule { }

