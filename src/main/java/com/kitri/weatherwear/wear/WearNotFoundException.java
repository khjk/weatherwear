package com.kitri.weatherwear.wear;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//404 ERROR
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WearNotFoundException extends RuntimeException {
    public WearNotFoundException(String message) {
        super(message);
    }
}

