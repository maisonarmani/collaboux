import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {EventListComponent} from "./list/event-list.component";
import {EventRouteActivator} from "../shared/event.route.activator";




const routes: Routes = [
  {path:'',loadChildren:'../onboarding/onboarding.module#OnboardingModule'},
  {path:'auth',loadChildren:'../onboarding/onboarding.module#OnboardingModule'},
  {path:'event', component:EventListComponent, canActivate:[EventRouteActivator] , children:[
      {path:'list', component:EventListComponent, canActivate:[EventRouteActivator]},
    ]
  },
  {path:'user',loadChildren:'../user/user.module#UserModule'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)] ,
  exports: [RouterModule],
  providers:[EventRouteActivator]
})
export class EventRoutingModule { }

