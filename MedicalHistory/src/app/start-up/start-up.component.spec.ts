import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartUpComponent } from './start-up.component';

describe('StartUpComponent', () => {
  let component: StartUpComponent;
  let fixture: ComponentFixture<StartUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StartUpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StartUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
