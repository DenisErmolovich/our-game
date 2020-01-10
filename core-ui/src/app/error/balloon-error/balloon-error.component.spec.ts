import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BalloonErrorComponent } from './balloon-error.component';

describe('BallonErrorComponent', () => {
  let component: BalloonErrorComponent;
  let fixture: ComponentFixture<BalloonErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BalloonErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BalloonErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
