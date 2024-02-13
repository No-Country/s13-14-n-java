package com.latam.companerosDeViajeAPI.controller.exception;

import com.latam.companerosDeViajeAPI.dto.exceptions.ErrorResponseDto;
import com.latam.companerosDeViajeAPI.exceptions.UsernameOrPasswordIncorretException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors().stream().map(ErrorResponseDto::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(UsernameOrPasswordIncorretException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(UsernameOrPasswordIncorretException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDto("Incorrect Username or password", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
