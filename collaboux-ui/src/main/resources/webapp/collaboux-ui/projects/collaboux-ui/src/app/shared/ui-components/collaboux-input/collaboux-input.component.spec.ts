import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollabouxInputComponent } from './collaboux-input.component';

describe('CollabouxInputComponent', () => {
  let component: CollabouxInputComponent;
  let fixture: ComponentFixture<CollabouxInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollabouxInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollabouxInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
