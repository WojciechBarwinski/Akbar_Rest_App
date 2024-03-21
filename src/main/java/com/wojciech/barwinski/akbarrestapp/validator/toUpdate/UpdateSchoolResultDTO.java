package com.wojciech.barwinski.akbarrestapp.validator.toUpdate;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.ValidationReportFromUpdateSchool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateSchoolResultDTO {

    private ValidationReportFromUpdateSchool report;
    private SchoolToViewDTO school;
}
