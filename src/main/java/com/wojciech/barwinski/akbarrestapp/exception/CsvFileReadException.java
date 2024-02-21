package com.wojciech.barwinski.akbarrestapp.exception;

public class CsvFileReadException extends ReaderException{

    public CsvFileReadException(String message) {
        super(message);
    }

    public CsvFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
