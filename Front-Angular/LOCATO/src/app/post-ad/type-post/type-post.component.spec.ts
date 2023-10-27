import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypePostComponent } from './type-post.component';

describe('TypePostComponent', () => {
  let component: TypePostComponent;
  let fixture: ComponentFixture<TypePostComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TypePostComponent]
    });
    fixture = TestBed.createComponent(TypePostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
