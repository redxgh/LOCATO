import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentingAdComponent } from './renting-ad.component';

describe('RentingAdComponent', () => {
  let component: RentingAdComponent;
  let fixture: ComponentFixture<RentingAdComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentingAdComponent]
    });
    fixture = TestBed.createComponent(RentingAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
