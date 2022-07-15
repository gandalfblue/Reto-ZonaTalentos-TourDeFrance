package com.co.sofka.api.tourfrance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionPersonalityNotFound extends RuntimeException{

    private static final long serialVersionUID = -4799000789758405712L;

    public ExceptionPersonalityNotFound(String message) {
        super(message);
    }
}
