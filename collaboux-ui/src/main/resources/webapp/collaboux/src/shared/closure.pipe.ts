import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name:'closure'})
export class ClosurePipe implements PipeTransform{

  transform(value: any, ...args): string {
    return `"${value} transformed"`
  }
}
