package com.example.cookie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "")
public class FileUploadFailedException extends RuntimeException {

    public FileUploadFailedException() {
        super();
    }

    public FileUploadFailedException(String message) {
        super(message);
    }

    public FileUploadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
