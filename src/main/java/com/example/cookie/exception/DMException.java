package com.example.cookie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 공통으로 쓰이는 Exception
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DMException extends RuntimeException {

    private String errorCode;

    public DMException() {
    }

    public DMException(String message) {
        super(message);
    }

    public DMException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DMException(String message, Throwable cause) {
        super(message, cause);
    }

    public DMException(Throwable cause) {
        super(cause);
    }

    public DMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}