package com.gustavo.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustavo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.gustavo.helpdesk.services.exceptions.ObjectNotFoundException;

@ControllerAdvice // Aplicado global para todas as controllers
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) // Manipulador de exceções do tipo ObjectNotFound
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, 
    HttpServletRequest request) {

        StandardError error = new StandardError(
            System.currentTimeMillis(), 
            HttpStatus.NOT_FOUND.value(), 
            "Object not found", 
            ex.getMessage(), 
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // 
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, 
    HttpServletRequest request) {

        StandardError error = new StandardError(
            System.currentTimeMillis(), 
            HttpStatus.BAD_REQUEST.value(), 
            "Violação de dados", 
            ex.getMessage(), 
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // 
    public ResponseEntity<ValidationError> validationErrors(MethodArgumentNotValidException ex, 
    HttpServletRequest request) {

        ValidationError errors = new ValidationError(
            System.currentTimeMillis(), 
            HttpStatus.BAD_REQUEST.value(), 
            "Validation error", 
            "Erro na validação de campos", 
            request.getRequestURI()
        );

        for(FieldError x : ex.getBindingResult().getFieldErrors()){
            errors.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    

}
