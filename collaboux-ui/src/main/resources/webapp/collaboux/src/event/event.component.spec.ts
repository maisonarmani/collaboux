import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { EventComponent } from './event.component';

describe('EventComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        EventComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(EventComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'collaboux'`, () => {
    const fixture = TestBed.createComponent(EventComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('collaboux');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(EventComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('collaboux app is running!');
  });
});
