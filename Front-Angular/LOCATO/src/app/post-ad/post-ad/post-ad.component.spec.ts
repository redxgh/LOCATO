import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostAdComponent } from './post-ad.component';

describe('PostAdComponent', () => {
  let component: PostAdComponent;
  let fixture: ComponentFixture<PostAdComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostAdComponent]
    });
    fixture = TestBed.createComponent(PostAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
