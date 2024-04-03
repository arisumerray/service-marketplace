import { Component, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { PostService } from '../service/post.service';

@Component({
  selector: 'app-post-list',
  standalone: true,
  imports: [],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.css'
})
export class PostListComponent implements OnInit {
    posts: Post[];

    constructor(private postService: PostService) {
    }

    ngOnInit() {
      this.postService.findAll().subscribe(data => {
        this.posts = data;
      });
    }
}
