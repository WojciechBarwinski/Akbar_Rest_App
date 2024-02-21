package com.wojciech.barwinski.akbarrestapp.exception;

public class InvalidCsvDataException extends ReaderException{

    public InvalidCsvDataException(String message) {
        super(message);
    }

    public InvalidCsvDataException(String message, Throwable cause) {
        super(message, cause);
    }
}