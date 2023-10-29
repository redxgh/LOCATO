import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoommateAdComponent } from './roommate-ad.component';

describe('RoommateAdComponent', () => {
  let component: RoommateAdComponent;
  let fixture: ComponentFixture<RoommateAdComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoommateAdComponent]
    });
    fixture = TestBed.createComponent(RoommateAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
