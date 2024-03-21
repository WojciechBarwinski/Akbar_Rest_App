package com.wojciech.barwinski.akbarrestapp.validator.toUpload;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UploadSchoolResultDTO {

    private List<ValidationReportFromImportingSchool> schoolValidateReports;
    private List<SchoolToRosterDTO> schoolToRosterDTOS;

}
