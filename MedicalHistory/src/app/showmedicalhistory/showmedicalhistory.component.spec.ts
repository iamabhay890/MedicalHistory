import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowmedicalhistoryComponent } from './showmedicalhistory.component';

describe('ShowmedicalhistoryComponent', () => {
  let component: ShowmedicalhistoryComponent;
  let fixture: ComponentFixture<ShowmedicalhistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowmedicalhistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowmedicalhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
