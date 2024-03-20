package com.wojciech.barwinski.akbarrestapp.exception;

import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromUpdateSchool;

public class SchoolUpdateException extends SchoolException{

    public SchoolUpdateException(ValidationReportFromUpdateSchool reportFromValidation, String message) {
        super(message);
    }
}
