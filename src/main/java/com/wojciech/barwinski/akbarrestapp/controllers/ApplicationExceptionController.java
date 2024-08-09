package com.wojciech.barwinski.akbarrestapp.controllers;


import com.wojciech.barwinski.akbarrestapp.exception.*;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.ValidationReportFromUpdateSchool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StaffNotFoundException.class)
    public String staffNotFoundException(PhoneNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
