package com.latam.companerosDeViajeAPI.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelGroupClosedException extends RuntimeException {
    private String field;
    public TravelGroupClosedException(String s, String field) {
        super(s);
        this.field = field;
    }
}
