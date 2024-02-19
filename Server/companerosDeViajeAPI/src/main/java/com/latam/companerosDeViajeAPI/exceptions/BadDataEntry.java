package com.latam.companerosDeViajeAPI.exceptions;

import lombok.Data;

@Data
public class BadDataEntry extends RuntimeException {
    private String field;
    public BadDataEntry(String s, String field) {
        super(s);
        this.field= field;
    }

}
