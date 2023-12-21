package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidateReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidationResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus.*;

@Component
public class SchoolRepresentationValidator {


    public SchoolRepValidationResult schoolsValidate(List<SchoolCsvRepresentation> schoolsRep) {
        List<SchoolRepValidateReport> reports = new ArrayList<>();
        List<SchoolCsvRepresentation> correctSchools = new ArrayList<>();

        for (int i = 0; i < schoolsRep.size(); i++) {
            SchoolCsvRepresentation school = schoolsRep.get(i);
            SchoolRepValidateReport report = validateOneSchool(school, i);
            reports.add(report);
            if (report.getStatus() != ERROR) {
                correctSchools.add(school);
            }
        }
        return new SchoolRepValidationResult(reports, correctSchools);
    }


    private SchoolRepValidateReport validateOneSchool(SchoolCsvRepresentation school, int index) {
        SchoolRepValidateReport report = new SchoolRepValidateReport(index);
        List<FieldReport> fields = new ArrayList<>();

        fields.add(checkRSPO(school.getRspo()));


        report.setStatus(setReportStatus(fields));
        report.setFieldsReports(fields);

        System.out.println("------------------- report-----------");
        System.out.println(report.getStatus());
        System.out.println(report.getFieldsReports());
        return report;
    }

    private ValidationStatus setReportStatus(List<FieldReport> fieldsReports){

        if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ERROR)) {
            return ERROR;
        } else if (fieldsReports.stream().anyMatch(report -> report.getStatus() == WARNING)) {
            return WARNING;
        } else {
            return OK;
        }
    }

    private FieldReport checkRSPO(String rspo) {
        FieldReport fieldReport = new FieldReport("RSPO", OK);

        if (rspo.isBlank() || rspo.matches(".*[a-zA-Z].*")) {
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("RSPO is null or have non-digit signs");
            return fieldReport;
        }
        if (!rspo.matches(".*\\d{3,8}.*")) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("RSPO has untypical size");
            return fieldReport;
        }

        fieldReport.setComment("OK");
        return fieldReport;
    }

}
