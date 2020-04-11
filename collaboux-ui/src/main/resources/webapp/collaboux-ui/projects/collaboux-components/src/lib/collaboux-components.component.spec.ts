import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollabouxComponentsComponent } from './collaboux-components.component';

describe('CollabouxComponentsComponent', () => {
  let component: CollabouxComponentsComponent;
  let fixture: ComponentFixture<CollabouxComponentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollabouxComponentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollabouxComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
