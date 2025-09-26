package com.example.todoapp.handler;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.exception.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 Not Found hatasını yakalar
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>("{\"error\": \"" + ex.getMessage() + "\"}", HttpStatus.NOT_FOUND);
    }

    // 409 Conflict hatasını yakalar (DuplicateException)
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateException ex) {
        return new ResponseEntity<>("{\"error\": \"" + ex.getMessage() + "\"}", HttpStatus.CONFLICT);
    }
}