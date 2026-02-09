package com.example.todoapp.service;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.exception.DuplicateException;
import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.mapper.TodoMapper;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoDTO> findAll() {
        List<Todo> todos = todoRepository.findAll();
        return todoMapper.toDTOList(todos);
    }

    public TodoDTO findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + id));
        return todoMapper.toDTO(todo);
    }

    public TodoDTO save(TodoDTO todoDTO) {
        Optional<Todo> existingTodo = todoRepository.findByTitle(todoDTO.getTitle());

        if (existingTodo.isPresent()) {
            throw new DuplicateException("Todo already exists with title: " + todoDTO.getTitle());
        }
        Todo todo = todoMapper.toEntity(todoDTO);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDTO(savedTodo);

    }

    public void deleteById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new NotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}