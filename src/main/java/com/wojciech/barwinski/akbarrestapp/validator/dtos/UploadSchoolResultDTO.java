package com.wojciech.barwinski.akbarrestapp.validator.dtos;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UploadSchoolResultDTO {

    private List<ValidationReportFromImportingSchool> schoolValidateReports;
    private List<ShortSchoolDTO> shortSchoolDTOS;

}
