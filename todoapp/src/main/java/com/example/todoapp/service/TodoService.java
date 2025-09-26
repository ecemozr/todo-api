package com.example.todoapp.service;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) { // Optional yerine doğrudan Todo döndürür
        return todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
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
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        todoRepository.deleteById(id);
    }
}