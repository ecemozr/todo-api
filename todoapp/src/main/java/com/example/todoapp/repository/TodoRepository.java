package com.example.todoapp.repository;

import com.example.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Modifying // Veritabanında değişiklik (update/delete) yapacağımızı belirtir
    @Transactional // Bu işlemin bir transaction içinde olması şarttır
    @Query("DELETE FROM Todo t WHERE t.completed = true")
    int deleteCompletedTodos();
    Optional<Todo> findByTitle(String title);
}