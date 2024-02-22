package com.wojciech.barwinski.akbarrestapp.Controllers;


import com.wojciech.barwinski.akbarrestapp.exception.InvalidCsvDataException;
import com.wojciech.barwinski.akbarrestapp.exception.ReaderException;
import com.wojciech.barwinski.akbarrestapp.exception.WrongFileTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ApplicationExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ExceptionHandler(WrongFileTypeException.class)
    public String wrongFileTypeException(Exception e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //422
    @ExceptionHandler(InvalidCsvDataException.class)
    public String invalidCsvDataException(Exception e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    @ExceptionHandler(ReaderException.class)
    public String readerException(Exception e) {
        return e.getMessage();
    }

}
