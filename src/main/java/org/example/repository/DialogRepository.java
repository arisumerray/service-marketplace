package org.example.repository;

import org.example.entity.Dialog;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Integer> {
    @Query(value = "SELECT d.* " +
            "FROM public.dialogs d " +
            "JOIN users_dialogs ud ON d.id = ud.dialog_id " +
            "JOIN users u ON ud.user_id = u.id " +
            "WHERE u.id = ?1", nativeQuery = true)
    List<Dialog> findAllByUserId(Integer id);
}
