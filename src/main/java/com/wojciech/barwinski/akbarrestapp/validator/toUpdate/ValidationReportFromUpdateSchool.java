package com.wojciech.barwinski.akbarrestapp.validator.toUpdate;

import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.FieldReportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ValidationReportFromUpdateSchool {

    private ValidationStatus status;
    private Long rspo;
    private List<FieldReportDTO> fieldsReports;

}
