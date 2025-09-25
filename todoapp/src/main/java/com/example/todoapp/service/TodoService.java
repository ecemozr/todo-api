package com.example.todoapp.service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> updateTodo(Long id, Todo updatedTodo) {
        return todoRepository.findById(id).map(todo -> {
            // Artık bu metotlar elle eklendiği için hata vermeyecek:
            if (updatedTodo.getTitle() != null) {
                todo.setTitle(updatedTodo.getTitle());
            }
            if (updatedTodo.getDescription() != null) {
                todo.setDescription(updatedTodo.getDescription());
            }
            if (updatedTodo.isCompleted()) {
                todo.setCompleted(updatedTodo.isCompleted());
            }
            return todoRepository.save(todo);
        });
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}