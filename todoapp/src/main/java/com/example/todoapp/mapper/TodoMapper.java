package com.example.todoapp.mapper;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.model.Todo;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoDTO toDTO(Todo todo);

    Todo toEntity(TodoDTO todoDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateEntity(TodoDTO dto, @MappingTarget Todo entity);

    List<TodoDTO> toDTOList(List<Todo> todos);
}