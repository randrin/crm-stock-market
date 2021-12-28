package com.crm.market.stock.exception;

import lombok.Getter;

import java.util.List;

public class BadCredentialsException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;

    @Getter
    private List<String> errors;

    public BadCredentialsException(String message) {
        super(message);
    }

    public BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCredentialsException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public BadCredentialsException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public BadCredentialsException(String message, ErrorCodes errorCodes, List<String> errors) {
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }
}
