package com.wojciech.barwinski.akbarrestapp.exception;

public class WrongFileTypeException extends ReaderException{

    public WrongFileTypeException(String message) {
        super(message);
    }

    public WrongFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
