package com.example.todoapp.mapper;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.model.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", implementationName = "TodoMapperImplGenerated")
public interface TodoMapper {

    TodoDTO toDTO(Todo todo);

    Todo toEntity(TodoDTO todoDTO);

    List<TodoDTO> toDTOList(List<Todo> todos);
}