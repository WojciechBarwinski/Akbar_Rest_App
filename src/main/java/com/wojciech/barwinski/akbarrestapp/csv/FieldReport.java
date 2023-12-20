package com.wojciech.barwinski.akbarrestapp.csv;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldReport {

    private String fieldName;
    private CsvValidationStatus status;
    private String comment;
}
