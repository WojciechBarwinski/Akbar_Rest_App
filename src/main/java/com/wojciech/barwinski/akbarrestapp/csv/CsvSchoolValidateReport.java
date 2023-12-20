package com.wojciech.barwinski.akbarrestapp.csv;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CsvSchoolValidateReport {

    private CsvValidationStatus status;
    private int indexInCsv;
    private List<FieldReport> fieldsReports;


}
