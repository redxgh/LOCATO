import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditRoommateAdComponent } from './edit-roommate-ad.component';

describe('EditRoommateAdComponent', () => {
  let component: EditRoommateAdComponent;
  let fixture: ComponentFixture<EditRoommateAdComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditRoommateAdComponent]
    });
    fixture = TestBed.createComponent(EditRoommateAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
