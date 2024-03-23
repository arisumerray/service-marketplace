package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from public.users where email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "select * from public.users where phone_number = ?1", nativeQuery = true)
    Optional<User> findByPhoneNumber(String email);
}
