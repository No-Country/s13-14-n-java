package com.latam.companerosDeViajeAPI.exceptions;

import lombok.Getter;

@Getter
public class UserOutsideTheGroupException extends RuntimeException{
    private String field;
    public UserOutsideTheGroupException(String s, String field) {
        super(s);
        this.field=field;
    }
}
