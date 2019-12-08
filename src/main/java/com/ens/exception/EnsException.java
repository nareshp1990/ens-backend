package com.ens.exception;

public class EnsException extends RuntimeException {

    public EnsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnsException(String message) {
        super(message);
    }

    public EnsException() {
        super();
    }
}
