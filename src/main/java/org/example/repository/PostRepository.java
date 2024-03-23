package org.example.repository;

import org.example.entity.Post;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * from public.posts where is_executed = false", nativeQuery = true)
    public List<Post> getAllNotExecutedPosts();
    @Query(value = "select * from public.posts where is_executed = false and is_selling = true", nativeQuery = true)
    public List<Post> getAllNotExecutedSellingPosts();

    @Query(value = "select * from public.posts where is_executed = false and is_selling = false", nativeQuery = true)
    public List<Post> getAllNotExecutedNotSellingPosts();
}
