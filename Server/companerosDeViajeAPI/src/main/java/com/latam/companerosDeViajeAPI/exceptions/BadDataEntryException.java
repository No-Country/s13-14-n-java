package com.latam.companerosDeViajeAPI.exceptions;

import lombok.Data;

@Data
public class BadDataEntryException extends RuntimeException {
    private String field;
    public BadDataEntryException(String s, String field) {
        super(s);
        this.field= field;
    }

}
