package com.example.todoapp.repository;

import com.example.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String title);
}