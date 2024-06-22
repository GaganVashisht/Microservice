package com.project.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse response=ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleResourceAlreadyExists(ResourceAlreadyExistException ex){
        String message =ex.getMessage();
        ApiResponse response=ApiResponse.builder().message(message).success(true).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
