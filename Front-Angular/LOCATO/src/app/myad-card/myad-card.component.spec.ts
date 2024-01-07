import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyadCardComponent } from './myad-card.component';

describe('MyadCardComponent', () => {
  let component: MyadCardComponent;
  let fixture: ComponentFixture<MyadCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyadCardComponent]
    });
    fixture = TestBed.createComponent(MyadCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
