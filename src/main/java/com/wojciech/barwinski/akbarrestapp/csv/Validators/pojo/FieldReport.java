package com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo;


import com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldReport {

    private String fieldName;
    private ValidationStatus status;
    private String comment;

    public FieldReport(String fieldName, ValidationStatus status) {
        this.fieldName = fieldName;
        this.status = status;
    }
}
