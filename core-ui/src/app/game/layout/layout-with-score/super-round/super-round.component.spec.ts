import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperRoundComponent } from './super-round.component';

describe('SuperRoundComponent', () => {
  let component: SuperRoundComponent;
  let fixture: ComponentFixture<SuperRoundComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuperRoundComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperRoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
