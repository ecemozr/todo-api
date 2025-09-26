package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Long id) {
        // Hata yönetimi (NotFoundException) Service katmanında yapılır
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        // Hata yönetimi (DuplicateException) Service katmanında yapılır
        Todo savedTodo = todoService.save(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        // Burada update metodumuz olmadığı için save metodunu kullanıyoruz
        // Normalde burada TodoController içinde update metodu olurdu.
        // Hata yönetimi (NotFoundException) Service katmanında yapılır
        todo.setId(id);
        Todo updatedTodo = todoService.save(todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        // Hata yönetimi (NotFoundException) Service katmanında yapılır
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}