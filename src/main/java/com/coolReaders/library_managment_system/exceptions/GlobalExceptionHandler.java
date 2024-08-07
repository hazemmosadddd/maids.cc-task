package com.coolReaders.library_managment_system.exceptions ; 

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.coolReaders.library_managment_system.exceptions.definedexceptions.AuthenticationException;
import com.coolReaders.library_managment_system.exceptions.definedexceptions.PermissionDeniedException;
import com.coolReaders.library_managment_system.exceptions.definedexceptions.ResourceNotFoundException;
import com.coolReaders.library_managment_system.responses.ApiError;
import com.coolReaders.library_managment_system.responses.ErrorResponse;



@ControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
       List<ApiError> apiErrors = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new ApiError("Validation error in field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage()))
            .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorResponse(apiErrors));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return null ; 

    }

    
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        return null ; 
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ErrorResponse> handlePermissionDeniedException(PermissionDeniedException ex) {
        return null ; 

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return null ; 


    }
}

