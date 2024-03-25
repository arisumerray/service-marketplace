package org.example.repository;

import org.example.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query(value = "select * from public.feedback where user_id = ?1", nativeQuery = true)
    List<Feedback> findAllByUserId(Integer id);
}
