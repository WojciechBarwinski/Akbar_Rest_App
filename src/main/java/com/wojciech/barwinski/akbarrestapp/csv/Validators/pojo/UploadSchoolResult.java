package com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UploadSchoolResult {

    private List<SchoolRepValidateReport> schoolValidateReports;
    private List<SchoolDTOPreview> schoolDTOPreviews;

}