package com.wojciech.barwinski.akbarrestapp.controllers;


import com.wojciech.barwinski.akbarrestapp.exception.*;
import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.ValidationReportFromUpdateSchool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ApplicationExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongFileTypeException.class)
    public String wrongFileTypeException(WrongFileTypeException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidCsvDataException.class)
    public String invalidCsvDataException(InvalidCsvDataException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ReaderException.class)
    public String readerException(ReaderException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdMismatchException.class)
    public String idAndRspoMismatch(IdMismatchException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SchoolUpdateException.class)
    public ValidationReportFromUpdateSchool wrongDataFromUpdateSchool(SchoolUpdateException e){
        return e.getReportFromValidation();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SchoolNotFoundException.class)
    public String schoolNotFoundException(SchoolNotFoundException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PhoneNotFoundException.class)
    public String schoolNotFoundException(PhoneNotFoundException e){
        return e.getMessage();
    }
}
