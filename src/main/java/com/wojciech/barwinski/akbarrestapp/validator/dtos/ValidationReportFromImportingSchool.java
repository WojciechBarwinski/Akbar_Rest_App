package com.wojciech.barwinski.akbarrestapp.validator.dtos;

import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationReportFromImportingSchool {

    private ValidationStatus status;
    private String rspo;
    private int indexInCsv;
    private List<FieldReportDTO> fieldsReports;

    public ValidationReportFromImportingSchool(int indexInCsv, String rspo) {
        this.rspo = rspo;
        this.indexInCsv = indexInCsv;
    }
}
