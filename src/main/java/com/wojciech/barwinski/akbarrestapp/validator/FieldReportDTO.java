package com.wojciech.barwinski.akbarrestapp.validator;


import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldReportDTO {

    private String fieldName;
    private ValidationStatus status;
    private String comment;

    public FieldReportDTO(String fieldName) {
        this.fieldName = fieldName;
        this.status = ValidationStatus.OK;
        this.comment = "OK";
    }
}
