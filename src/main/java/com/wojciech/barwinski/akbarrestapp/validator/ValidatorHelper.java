package com.wojciech.barwinski.akbarrestapp.validator;

import java.util.List;

public class ValidatorHelper {

    public static ValidationStatus setReportStatus(List<FieldReportDTO> fieldsReports) {

        if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ValidationStatus.ERROR)) {
            return ValidationStatus.ERROR;
        } else if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ValidationStatus.WARNING)) {
            return ValidationStatus.WARNING;
        } else {
            return ValidationStatus.OK;
        }
    }
}
