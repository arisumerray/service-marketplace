import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostsUslugiComponent } from './posts-uslugi.component';

describe('PostsUslugiComponent', () => {
  let component: PostsUslugiComponent;
  let fixture: ComponentFixture<PostsUslugiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostsUslugiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PostsUslugiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
