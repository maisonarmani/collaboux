import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SelectTypeComponent} from "./select-type/select-type.component";
import {AddInformationComponent} from "./add-information/add-information.component";
import {CompletionComponent} from "./completion/completion.component";
import {SetupResolverService} from "./setup-resolver.service";
import {SetupComponent} from "./setup.component";


const routes: Routes = [
  {
    path:'',
    component:SetupComponent,
    children:[
      { path:'', component:SelectTypeComponent, resolve:[SetupResolverService]},
      { path:'select-type', component:SelectTypeComponent, resolve:[SetupResolverService]},
      { path:'add-information', component:AddInformationComponent, resolve:[SetupResolverService],data: {animation: 'Homepage'} },
      { path:'complete', component:CompletionComponent, resolve:[SetupResolverService]},
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SetupRoutingModule { }
