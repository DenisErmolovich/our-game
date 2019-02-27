import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutWithScoreComponent } from './layout-with-score.component';

describe('LayoutWithScoreComponent', () => {
  let component: LayoutWithScoreComponent;
  let fixture: ComponentFixture<LayoutWithScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LayoutWithScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LayoutWithScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
