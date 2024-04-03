import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../service/post.service';
import { Post } from '../model/post';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-post-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.css'
})
export class PostFormComponent {
    post: Post;

    constructor(
      private route: ActivatedRoute,
        private router: Router,
          private postService: PostService) {
      this.post = new Post();
    }

    onSubmit() {
      this.postService.save(this.post).subscribe(result => this.gotoPostList());
    }

    gotoPostList() {
      this.router.navigate(['/posts']);
    }
}
