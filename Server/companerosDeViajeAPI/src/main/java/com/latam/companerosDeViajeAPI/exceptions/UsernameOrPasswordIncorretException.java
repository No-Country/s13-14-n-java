package com.latam.companerosDeViajeAPI.exceptions;

public class UsernameOrPasswordIncorretException extends RuntimeException {
    public UsernameOrPasswordIncorretException(String message) {
        super(message);
    }
}
