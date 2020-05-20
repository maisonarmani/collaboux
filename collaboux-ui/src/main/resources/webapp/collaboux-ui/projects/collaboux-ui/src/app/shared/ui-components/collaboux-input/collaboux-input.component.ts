import {
  Component,
  ElementRef,
  Input,
  OnInit, Self
} from '@angular/core';
import {ControlValueAccessor,NgControl} from "@angular/forms";

@Component({
  selector: 'collaboux-input',
  templateUrl: './collaboux-input.component.html',
  styleUrls: ['./collaboux-input.component.scss'],
  host:{
    "(change)":"onChange($event.target.value)",
    "(blur)":"onTouched()"
  },

})
export class CollabouxInputComponent implements OnInit, ControlValueAccessor {

  @Input()
  public type:String;

  @Input()
  public label:String;

  @Input('value')
  public val:String;

  @Input()
  public placeholder:String = '';

  @Input()
  public name:String;

  public onChange: (_: any) => void;
  public onTouched: () => void;

  public required: string;

  constructor(private hostElement: ElementRef, @Self() public self: NgControl) {
    this.onChange = (_: any) => {};
    this.onTouched = () => {};
    this.self.valueAccessor = this;
  }


  ngOnInit(): void {
    this.hostElement.nativeElement.classList.add(...["display-flex","flex-column","p-relative"]); // Or do @HostBinding
  }

  writeValue(obj: any) {
    this.val = obj;
  }

  registerOnChange(fn: any) {
    this.onChange=fn;
  }

  registerOnTouched(fn: any) {
    this.onTouched=fn;
  }

  setDisabledState?(isDisabled: boolean) { }

}
