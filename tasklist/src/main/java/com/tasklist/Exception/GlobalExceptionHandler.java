package com.tasklist.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionModel> handleResponseStatusException(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode()).body(
                new ExceptionModel(
                        e.getStatusCode().value(),
                        e.getClass().getSimpleName(),
                        e.getReason(),e.getMessage())
        );
    }
}
