package com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolRepValidateReport {

    private ValidationStatus status;
    private int indexInCsv;
    private List<FieldReport> fieldsReports;

    public SchoolRepValidateReport(int indexInCsv) {
        this.indexInCsv = indexInCsv;
    }
}
