package com.wojciech.barwinski.akbarrestapp.validator;

import com.wojciech.barwinski.akbarrestapp.validator.dtos.FieldReportDTO;

import java.util.List;

class ValidatorHelper {

    static ValidationStatus setReportStatus(List<FieldReportDTO> fieldsReports) {

        if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ValidationStatus.ERROR)) {
            return ValidationStatus.ERROR;
        } else if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ValidationStatus.WARNING)) {
            return ValidationStatus.WARNING;
        } else {
            return ValidationStatus.OK;
        }
    }
}
