package com.wojciech.barwinski.akbarrestapp.validator;

import com.wojciech.barwinski.akbarrestapp.validator.dtos.FieldReportDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromSchoolImportDTO;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolRepresentationValidator {


    public List<ValidationReportFromSchoolImportDTO> schoolsValidate(List<SchoolRepresentation> schoolsRep) {
        List<ValidationReportFromSchoolImportDTO> reports = new ArrayList<>();

        for (int i = 0; i < schoolsRep.size(); i++) {
            SchoolRepresentation school = schoolsRep.get(i);
            ValidationReportFromSchoolImportDTO report = validateSchool(school, i);
            reports.add(report);
        }
        return reports;
    }

    private ValidationReportFromSchoolImportDTO validateSchool(SchoolRepresentation school, int index) {

        ValidationReportFromSchoolImportDTO report = new ValidationReportFromSchoolImportDTO(index);
        List<FieldReportDTO> fields = new ArrayList<>();

        fields.add(FieldsChecker.checkRSPO(school.getRspo()));
        fields.add(FieldsChecker.checkSchoolType(school.getType()));
        fields.add(FieldsChecker.checkSchoolName(school.getName()));
        fields.add(FieldsChecker.checkSchoolStreet(school.getStreet()));
        fields.add(FieldsChecker.checkSchoolBuildingNumber(school.getBuildingNumber()));
        fields.add(FieldsChecker.checkSchoolLocalNumber(school.getLocalNumber()));
        fields.add(FieldsChecker.checkSchoolZipCode(school.getZipCode()));
        fields.add(FieldsChecker.checkSchoolCity(school.getCity()));
        fields.add(FieldsChecker.checkSchoolPhone(school.getPhone()));
        fields.add(FieldsChecker.checkSchoolEmail(school.getEmail()));
        fields.add(FieldsChecker.checkSchoolWebsite(school.getWebsite()));
        fields.add(FieldsChecker.checkSchoolVoivodeship(school.getVoivodeship()));
        fields.add(FieldsChecker.checkSchoolCounty(school.getCounty()));
        fields.add(FieldsChecker.checkSchoolBorough(school.getBorough()));
        fields.add(FieldsChecker.checkSchoolStatus(school.getStatus()));

        report.setStatus(setReportStatus(fields));
        report.setFieldsReports(fields);

        return report;
    }

    private ValidationStatus setReportStatus(List<FieldReportDTO> fieldsReports) {

        if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ValidationStatus.ERROR)) {
            return ValidationStatus.ERROR;
        } else if (fieldsReports.stream().anyMatch(report -> report.getStatus() == ValidationStatus.WARNING)) {
            return ValidationStatus.WARNING;
        } else {
            return ValidationStatus.OK;
        }
    }

}
