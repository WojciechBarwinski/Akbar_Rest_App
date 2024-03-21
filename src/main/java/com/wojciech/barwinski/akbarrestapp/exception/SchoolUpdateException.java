package com.wojciech.barwinski.akbarrestapp.exception;

import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.ValidationReportFromUpdateSchool;
import lombok.Getter;

@Getter
public class SchoolUpdateException extends SchoolException {

    ValidationReportFromUpdateSchool reportFromValidation;

    public SchoolUpdateException(ValidationReportFromUpdateSchool reportFromValidation, String message) {
        super(message);
        this.reportFromValidation = reportFromValidation;
    }
}
