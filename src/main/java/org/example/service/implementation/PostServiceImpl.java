package org.example.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.PostMapper;
import org.example.dto.CreatePostDTO;
import org.example.entity.Post;
import org.example.repository.PostRepository;
import org.example.repository.UserRepository;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostMapper postMapper;

    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public List<Post> getAllNotExecutedPosts(String keyword) {
        return postRepository
                .getAllNotExecutedPosts(keyword == null ? "" : keyword.toLowerCase());
    }

    public List<Post> getAllNotExecutedSellingPosts() {
        return postRepository.getAllNotExecutedSellingPosts();
    }

    public List<Post> getAllNotExecutedNotSellingPosts() {
        return postRepository.getAllNotExecutedNotSellingPosts();
    }

    public Post createPost(CreatePostDTO createPostDTO, String name) {
        var post = postMapper.fromDto(createPostDTO);
        var user = userRepository.findByEmail(name);
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthorId(user.orElseThrow(() -> new RuntimeException("Error creating post. Please, try again")));
        post.setIsSelling(user.get().getRole().equals("USER_SELLER"));
        post.setIsExecuted(false);
        try {
            return postRepository.save(post);
        } catch (Exception e) {
            throw new RuntimeException("Error creating post. Please, try again");
        }
    }

    public Post updatePost(Integer id, CreatePostDTO createPostDTO, String name) {
        var post = postRepository.getReferenceById(id);
        if (!post.getAuthorId().getEmail().equals(name)) {
            throw new IllegalArgumentException("You can edit only your post");
        }
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        try {
            return postRepository.save(post);
        } catch (Exception e) {
            throw new RuntimeException("Error updating post. Please, try again");
        }
    }

    public void deletePost(Integer id, String name) {
        var post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new EntityNotFoundException("Post not found");
        } else if (!post.get().getAuthorId().getEmail().equals(name)) {
            throw new IllegalArgumentException("You can delete only your post");
        } else {
            try {
                postRepository.delete(post.get());
            } catch (Exception e) {
                throw new RuntimeException("Error deleting post. Please, try again");
            }
        }
    }
}
