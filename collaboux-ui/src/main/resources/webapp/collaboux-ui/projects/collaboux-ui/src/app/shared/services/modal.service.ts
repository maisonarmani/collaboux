import { Injectable } from '@angular/core';
import {DOMService} from "./dom.service";

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor(private domService: DOMService) { }

  private modalElementId = 'modal';
  private overlayElementId = 'overlay';

  init(component: any, inputs: object, outputs: object) {
    let componentConfig = {
      inputs:inputs,
      outputs:outputs
    };
    this.domService.appendComponentTo(this.modalElementId, component, componentConfig);

    document.getElementById(this.modalElementId).className = 'show';
    document.getElementById(this.overlayElementId).className = 'show';
  }

  destroy() {
    console.log("Destroying")
    this.domService.removeComponent();
    document.getElementById(this.modalElementId).className = 'hidden';
    document.getElementById(this.overlayElementId).className = 'hidden';
  }
}
