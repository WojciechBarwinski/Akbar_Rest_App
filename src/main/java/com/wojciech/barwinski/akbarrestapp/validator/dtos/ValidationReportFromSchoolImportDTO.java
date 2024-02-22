package com.wojciech.barwinski.akbarrestapp.validator.dtos;

import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationReportFromSchoolImportDTO {

    private ValidationStatus status;
    private int indexInCsv;
    private List<FieldReportDTO> fieldsReports;

    public ValidationReportFromSchoolImportDTO(int indexInCsv) {
        this.indexInCsv = indexInCsv;
    }
}
