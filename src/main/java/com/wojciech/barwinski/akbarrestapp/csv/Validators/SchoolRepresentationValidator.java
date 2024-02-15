package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csvCustomReder.SchoolCsvRepresentationDTO;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidateReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidationResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus.*;
import static com.wojciech.barwinski.akbarrestapp.csv.Validators.FieldsChecker.*;

@Component
public class SchoolRepresentationValidator {


    public SchoolRepValidationResult schoolsValidate(List<SchoolCsvRepresentationDTO> schoolsRep) {
        List<SchoolRepValidateReport> reports = new ArrayList<>();
        List<SchoolCsvRepresentationDTO> correctSchools = new ArrayList<>();

        for (int i = 0; i < schoolsRep.size(); i++) {
            SchoolCsvRepresentationDTO school = schoolsRep.get(i);
            SchoolRepValidateReport report = validateOneSchool(school, i);
            reports.add(report);
            if (report.getStatus() != ERROR) {
                correctSchools.add(school);
            }
        }
        return new SchoolRepValidationResult(reports, correctSchools);
    }

    private SchoolRepValidateReport validateOneSchool(SchoolCsvRepresentationDTO school, int index) {
        SchoolRepValidateReport report = new SchoolRepValidateReport(index);
        List<FieldReport> fields = new ArrayList<>();

        fields.add(checkRSPO(school.getRspo()));
        fields.add(checkSchoolType(school.getType()));
        fields.add(checkSchoolName(school.getName()));
        fields.add(checkSchoolStreet(school.getStreet()));
        fields.add(checkSchoolBuildingNumber(school.getBuildingNumber()));
        fields.add(checkSchoolLocalNumber(school.getLocalNumber()));
        fields.add(checkSchoolZipCode(school.getZipCode()));
        fields.add(checkSchoolCity(school.getCity()));
        fields.add(checkSchoolPhone(school.getPhone()));
        fields.add(checkSchoolEmail(school.getEmail()));
        fields.add(checkSchoolWebsite(school.getWebsite()));
        fields.add(checkSchoolVoivodeship(school.getVoivodeship()));
        fields.add(checkSchoolCounty(school.getCounty()));
        fields.add(checkSchoolBorough(school.getBorough()));
        fields.add(checkSchoolStatus(school.getStatus()));


        report.setStatus(setReportStatus(fields));
        report.setFieldsReports(fields);

        return report;
    }

    private ValidationStatus setReportStatus(List<FieldReport> fieldsReports) {

        if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ERROR)) {
            return ERROR;
        } else if (fieldsReports.stream().anyMatch(report -> report.getStatus() == WARNING)) {
            return WARNING;
        } else {
            return OK;
        }
    }

}
