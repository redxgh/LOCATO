import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MylistAdsComponent } from './mylist-ads.component';

describe('MylistAdsComponent', () => {
  let component: MylistAdsComponent;
  let fixture: ComponentFixture<MylistAdsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MylistAdsComponent]
    });
    fixture = TestBed.createComponent(MylistAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
