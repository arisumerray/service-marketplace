package org.example.controller;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.GroupSequence;
import lombok.RequiredArgsConstructor;
import org.example.dto.CreatePostDTO;
import org.example.entity.Post;
import org.example.entity.User;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(postService.getPostById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllNotExecutedPosts());
    }

    @GetMapping("/selling")
    public ResponseEntity<List<Post>> getAllSellingPosts() {
        return ResponseEntity.ok(postService.getAllNotExecutedSellingPosts());
    }

    @GetMapping("/buying")
    public ResponseEntity<List<Post>> getAllNotSellingPosts() {
        return ResponseEntity.ok(postService.getAllNotExecutedNotSellingPosts());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDTO createPostDTO, Principal principal) {
        return ResponseEntity.ok(postService.createPost(createPostDTO, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody CreatePostDTO createPostDTO, Principal principal) {
        try {
            return ResponseEntity.ok(postService.updatePost(id, createPostDTO, principal.getName()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id, Principal principal) {
        try {
            postService.deletePost(id, principal.getName());
            return ResponseEntity.status(204).body("Post deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
