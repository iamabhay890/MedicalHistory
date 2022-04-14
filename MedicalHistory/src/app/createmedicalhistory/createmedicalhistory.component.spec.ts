import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatemedicalhistoryComponent } from './createmedicalhistory.component';

describe('CreatemedicalhistoryComponent', () => {
  let component: CreatemedicalhistoryComponent;
  let fixture: ComponentFixture<CreatemedicalhistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatemedicalhistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatemedicalhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
