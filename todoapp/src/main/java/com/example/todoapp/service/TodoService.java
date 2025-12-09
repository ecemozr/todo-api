package com.example.todo.service;
import com.example.todo.exception.DuplicateException;
import com.example.todo.exception.NotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.TodoappApplication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoappApplication todoappApplication;

    public TodoService(TodoRepository todoRepository, TodoappApplication todoappApplication) {
        this.todoRepository = todoRepository;
        this.todoappApplication = todoappApplication;
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