package com.wojciech.barwinski.akbarrestapp.validator.dtos;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
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
