package com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SchoolRepValidationResult {
    
    private List<SchoolRepValidateReport> schoolValidateReports;
    private List<SchoolRepresentation> schoolsAfterValidate;
}
