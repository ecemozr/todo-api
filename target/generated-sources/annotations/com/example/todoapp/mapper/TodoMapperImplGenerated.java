package com.example.todoapp.mapper;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.model.Todo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-09T18:12:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TodoMapperImplGenerated implements TodoMapper {

    @Override
    public TodoDTO toDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId( todo.getId() );
        todoDTO.setTitle( todo.getTitle() );
        todoDTO.setDescription( todo.getDescription() );
        todoDTO.setCompleted( todo.isCompleted() );

        return todoDTO;
    }

    @Override
    public Todo toEntity(TodoDTO todoDTO) {
        if ( todoDTO == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( todoDTO.getId() );
        todo.setTitle( todoDTO.getTitle() );
        todo.setDescription( todoDTO.getDescription() );
        todo.setCompleted( todoDTO.isCompleted() );

        return todo;
    }

    @Override
    public List<TodoDTO> toDTOList(List<Todo> todos) {
        if ( todos == null ) {
            return null;
        }

        List<TodoDTO> list = new ArrayList<TodoDTO>( todos.size() );
        for ( Todo todo : todos ) {
            list.add( toDTO( todo ) );
        }

        return list;
    }
}
