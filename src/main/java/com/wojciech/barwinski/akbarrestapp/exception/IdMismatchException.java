package com.wojciech.barwinski.akbarrestapp.exception;

public class IdMismatchException extends ApplicationException{
    public IdMismatchException(String message) {
        super(message);
    }

    public IdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
