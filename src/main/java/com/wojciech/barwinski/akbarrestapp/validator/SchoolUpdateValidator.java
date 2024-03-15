package com.wojciech.barwinski.akbarrestapp.validator;

import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.FieldReportDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromUpdateSchool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.wojciech.barwinski.akbarrestapp.validator.PhoneToUpdateValidator.validatePhones;
import static com.wojciech.barwinski.akbarrestapp.validator.SchoolUpdateValidatorHelper.validateStringFieldsAreNotNullAndAreNotEmpty;
import static com.wojciech.barwinski.akbarrestapp.validator.ValidatorHelper.setReportStatus;

@Component
public class SchoolUpdateValidator {

    public ValidationReportFromUpdateSchool validateSchool(SchoolToUpdateDTO school) {

        ValidationReportFromUpdateSchool report = new ValidationReportFromUpdateSchool(school.getRspo());
        List<FieldReportDTO> reportFields = new ArrayList<>();

        reportFields.addAll(validateStringFieldsAreNotNullAndAreNotEmpty(SchoolToViewDTO.class.getDeclaredMethods(), school));
        reportFields.addAll(validateStringFieldsAreNotNullAndAreNotEmpty(AdditionalSchoolInformationDTO.class.getDeclaredMethods(),
                school.getAdditionalSchoolInformationDTO()));
        reportFields.addAll(validatePhones(school.getPhones()));


        report.setStatus(setReportStatus(reportFields));
        report.setFieldsReports(reportFields);
        return report;
    }


}
