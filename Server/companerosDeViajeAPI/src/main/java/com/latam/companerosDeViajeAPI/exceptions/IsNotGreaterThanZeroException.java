package com.latam.companerosDeViajeAPI.exceptions;

import lombok.Data;

@Data
public class IsNotGreaterThanZeroException extends RuntimeException {
    private String field;
    public IsNotGreaterThanZeroException(String s, String field) {
         super(s);
         this.field=field;
    }
}
