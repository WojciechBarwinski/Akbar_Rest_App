package com.wojciech.barwinski.akbarrestapp.exception;

public class SchoolException extends ApplicationException{
    public SchoolException(String message) {
        super(message);
    }

    public SchoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
