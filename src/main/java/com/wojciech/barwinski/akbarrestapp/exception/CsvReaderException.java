package com.wojciech.barwinski.akbarrestapp.exception;

public class CsvReaderException extends ReaderException{

    public CsvReaderException(String message) {
        super(message);
    }

    public CsvReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
