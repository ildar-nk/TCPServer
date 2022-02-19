package org.example.server.framework.exception;

public class RequestHeaderTooLargeException extends RuntimeException {
    public RequestHeaderTooLargeException() {
    }

    public RequestHeaderTooLargeException(String message) {
        super(message);
    }

    public RequestHeaderTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestHeaderTooLargeException(Throwable cause) {
        super(cause);
    }

    public RequestHeaderTooLargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
