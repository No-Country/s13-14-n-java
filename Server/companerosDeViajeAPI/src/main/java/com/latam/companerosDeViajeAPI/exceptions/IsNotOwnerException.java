package com.latam.companerosDeViajeAPI.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsNotOwnerException extends RuntimeException {
    private String field;
    public IsNotOwnerException(String s, String field) {
        super(s);
        this.field = field;
    }
}
