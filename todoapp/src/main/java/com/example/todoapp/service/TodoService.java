package com.example.todoapp.service;

import com.example.todoapp.exception.DuplicateException;
import com.example.todoapp.exception.NotFoundException;
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

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(Long id) {
        // Kayıt bulunamazsa NotFoundException fırlatır
        return todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));
    }

    public Todo save(Todo todo) {
        // MÜKERRERLİK KONTROLÜ
        Optional<Todo> existingTodo = todoRepository.findByTitle(todo.getTitle());

        if (existingTodo.isPresent()) {
            // Eğer kayıt varsa, 409 Conflict hatasını fırlat
            throw new DuplicateException("Todo already exists with title: " + todo.getTitle());
        }

        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new NotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}