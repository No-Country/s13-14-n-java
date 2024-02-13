package com.latam.companerosDeViajeAPI.dto.exceptions;

import org.springframework.validation.FieldError;

public record ErrorResponseDto(String field, String error) {
    public ErrorResponseDto(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
