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

        ValidationReportFromSchoolImportDTO report = new ValidationReportFromSchoolImportDTO(index, school.getRspo());
        List<FieldReportDTO> reportFields = new ArrayList<>();

        reportFields.add(FieldsChecker.checkRSPO(school.getRspo()));
        reportFields.add(FieldsChecker.checkSchoolType(school.getType()));
        reportFields.add(FieldsChecker.checkSchoolName(school.getName()));
        reportFields.add(FieldsChecker.checkSchoolStreet(school.getStreet()));
        reportFields.add(FieldsChecker.checkSchoolBuildingNumber(school.getBuildingNumber()));
        reportFields.add(FieldsChecker.checkSchoolLocalNumber(school.getLocalNumber()));
        reportFields.add(FieldsChecker.checkSchoolZipCode(school.getZipCode()));
        reportFields.add(FieldsChecker.checkSchoolCity(school.getCity()));
        reportFields.add(FieldsChecker.checkSchoolPhone(school.getPhone()));
        reportFields.add(FieldsChecker.checkSchoolEmail(school.getEmail()));
        reportFields.add(FieldsChecker.checkSchoolWebsite(school.getWebsite()));
        reportFields.add(FieldsChecker.checkSchoolVoivodeship(school.getVoivodeship()));
        reportFields.add(FieldsChecker.checkSchoolCounty(school.getCounty()));
        reportFields.add(FieldsChecker.checkSchoolBorough(school.getBorough()));
        reportFields.add(FieldsChecker.checkSchoolStatus(school.getStatus()));

        report.setStatus(setReportStatus(reportFields));
        report.setFieldsReports(reportFields);

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
