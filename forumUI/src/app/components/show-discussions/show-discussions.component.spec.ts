import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDiscussionsComponent } from './show-discussions.component';

describe('ShowDiscussionsComponent', () => {
  let component: ShowDiscussionsComponent;
  let fixture: ComponentFixture<ShowDiscussionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowDiscussionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowDiscussionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
