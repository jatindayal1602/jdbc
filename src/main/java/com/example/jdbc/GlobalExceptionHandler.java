package com.example.jdbc;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(UserNotFoundException.class)
//     public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
//         ErrorResponse error = new ErrorResponse(
//             ex.getMessage(),
//             "User not found",
//             HttpStatus.NOT_FOUND.value()
//         );
//         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
//         ErrorResponse error = new ErrorResponse(
//             "Internal Server Error",
//             ex.getMessage(),
//             HttpStatus.INTERNAL_SERVER_ERROR.value()
//         );
//         return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
 
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
 
	//  // Handle ProductNotFoundException
    // @ExceptionHandler(ProductNotFoundException.class)
    // public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
    //     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    // }

// Handle NoSuchElementException (Product not found)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
 
    // Handle IllegalArgumentException (Invalid input data)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
 
    // Handle DataIntegrityViolationException (Duplicate entry or constraint violation)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("Database Error: " + ex.getMessage(), HttpStatus.CONFLICT);
    }
 
    // Handle Validation Errors (If @Valid fails)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
 
    // Handle Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }}