package com.wojciech.barwinski.akbarrestapp.validator.toUpdate;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.validator.FieldReportDTO;
import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.wojciech.barwinski.akbarrestapp.validator.ValidatorHelper.setReportStatus;

@Component
public class SchoolUpdateValidator {


    public ValidationReportFromUpdateSchool validateSchool(SchoolToUpdateDTO school) {

        Set<ConstraintViolation<SchoolToUpdateDTO>> validate = getValidator().validate(school);
        List<FieldReportDTO> fieldsReports = createReportsFromValidation(validate);

        return ValidationReportFromUpdateSchool.builder()
                .rspo(school.getRspo())
                .fieldsReports(fieldsReports)
                .status(setReportStatus(fieldsReports))
                .build();
    }

    private static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    private List<FieldReportDTO> createReportsFromValidation(Set<ConstraintViolation<SchoolToUpdateDTO>> validate) {
        List<FieldReportDTO> fieldsReports = new ArrayList<>();

        for (ConstraintViolation<SchoolToUpdateDTO> xxx : validate) {
            FieldReportDTO fieldReport = new FieldReportDTO(xxx.getPropertyPath().toString());

            if (xxx.getMessage().contains("WARNING")) {
                fieldReport.setStatus(ValidationStatus.WARNING);
            } else {
                fieldReport.setStatus(ValidationStatus.ERROR);
            }

            fieldReport.setComment(xxx.getMessage());
            fieldsReports.add(fieldReport);
        }

        return fieldsReports;
    }
}
