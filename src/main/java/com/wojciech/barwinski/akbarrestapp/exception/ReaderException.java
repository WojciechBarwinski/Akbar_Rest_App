package com.wojciech.barwinski.akbarrestapp.exception;

public class ReaderException extends ApplicationException{

    public ReaderException(String message) {
        super(message);
    }

    public ReaderException(String message, Throwable cause){
        super(message, cause);
    }
}
