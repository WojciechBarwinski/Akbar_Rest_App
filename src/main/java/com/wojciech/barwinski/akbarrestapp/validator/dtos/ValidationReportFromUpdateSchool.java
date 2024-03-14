package com.wojciech.barwinski.akbarrestapp.validator.dtos;

import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationReportFromUpdateSchool {

    private ValidationStatus status;
    private Long rspo;
    private List<FieldReportDTO> fieldsReports;

    public ValidationReportFromUpdateSchool(Long rspo) {
        this.rspo = rspo;
    }
}
