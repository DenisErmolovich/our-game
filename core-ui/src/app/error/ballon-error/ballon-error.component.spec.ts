import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BallonErrorComponent } from './ballon-error.component';

describe('BallonErrorComponent', () => {
  let component: BallonErrorComponent;
  let fixture: ComponentFixture<BallonErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BallonErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BallonErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
