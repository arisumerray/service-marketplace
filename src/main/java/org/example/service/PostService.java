package org.example.service;

import org.example.dto.CreatePostDTO;
import org.example.entity.Post;

import java.util.List;

public interface PostService {

    Post getPostById(Integer id);
    List<Post> getAllNotExecutedPosts(String keyword);
    List<Post> getAllNotExecutedSellingPosts();

    List<Post> getAllNotExecutedNotSellingPosts();

    Post createPost(CreatePostDTO createPostDTO, String name);
    Post updatePost(Integer id, CreatePostDTO createPostDTO, String name);

    void deletePost(Integer id, String name);
}
