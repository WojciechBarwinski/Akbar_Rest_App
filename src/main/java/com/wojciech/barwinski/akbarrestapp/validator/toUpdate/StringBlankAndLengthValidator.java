package com.wojciech.barwinski.akbarrestapp.validator.toUpdate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringBlankAndLengthValidator implements ConstraintValidator<CorrectString, String> {

    private final String error = "ERROR";
    private final String warning = "WARNING";
    private final int minLength = 4;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error + " Pole nie posiada żadnej zawartości")
                    .addConstraintViolation();

            return false;
        }

        int length = value.length();
        if (length < minLength) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(warning + " Pole ma mniej niż " + minLength + " znaków.")
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}
