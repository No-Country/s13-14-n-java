package com.latam.companerosDeViajeAPI.controller.exception;

import com.latam.companerosDeViajeAPI.dto.exceptions.ErrorResponseDto;
import com.latam.companerosDeViajeAPI.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors().stream().map(ErrorResponseDto::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(UsernameOrPasswordIncorretException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(UsernameOrPasswordIncorretException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDto("Username or password", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchInterestException.class)
    public ResponseEntity<Object> NoSuchInterestException(NoSuchInterestException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDto("interest", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> SQLIntegrityConstraintViolationException (SQLIntegrityConstraintViolationException ex,WebRequest request){
        String originalMsg = ex.getMessage();
        String duplicateValue = ObtainDuplicateValue(originalMsg);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(duplicateValue +" is not available");
    }

    private String ObtainDuplicateValue(String msg) {
        String begin = "Duplicate entry '";
        String end = "' for key";
        int indexStart = msg.indexOf(begin) + begin.length();
        int indexEnd = msg.indexOf(end, indexStart);
        return msg.substring(indexStart, indexEnd);
    }

    @ExceptionHandler(BadDataEntryException.class)
    public ResponseEntity<ErrorResponseDto> BadDataEntryException(BadDataEntryException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(ex.getField(), ex.getMessage()));
    }
    @ExceptionHandler(IsNotGreaterThanZeroException.class)
    public ResponseEntity<ErrorResponseDto> IsNotGreaterThanZeroException(IsNotGreaterThanZeroException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(ex.getField(), ex.getMessage()));
    }
    @ExceptionHandler(UserNotValidException.class)
    public ResponseEntity<ErrorResponseDto> UserNotValidException(UserNotValidException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto("user", ex.getMessage()));
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> MissingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(ex.getParameterName(), ex.getMessage()));
    }
    @ExceptionHandler(UserOutsideTheGroupException.class)
    public ResponseEntity<ErrorResponseDto> UserOutsideTheGroupException(UserOutsideTheGroupException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(ex.getField(), ex.getMessage()));
    }

    @ExceptionHandler(NoSuchNotificationException.class)
    public ResponseEntity<ErrorResponseDto> NoSuchNotificationException(NoSuchNotificationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto("Notification", ex.getMessage()));
    }
}