package com.dev.bruno.insuranceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PriceCalculationException extends RuntimeException {

    public PriceCalculationException(Set<String> constraints) {
        super(String.join(", ", constraints));
    }
}
