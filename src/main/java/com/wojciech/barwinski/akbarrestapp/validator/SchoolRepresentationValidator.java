package com.wojciech.barwinski.akbarrestapp.validator;

import com.wojciech.barwinski.akbarrestapp.validator.dtos.FieldReportDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromImportingSchool;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.wojciech.barwinski.akbarrestapp.validator.ValidatorHelper.setReportStatus;

@Component
public class SchoolRepresentationValidator {


    public List<ValidationReportFromImportingSchool> schoolsValidate(List<SchoolRepresentation> schoolsRep) {
        List<ValidationReportFromImportingSchool> reports = new ArrayList<>();

        for (int i = 0; i < schoolsRep.size(); i++) {
            SchoolRepresentation school = schoolsRep.get(i);
            ValidationReportFromImportingSchool report = validateSchool(school, i);
            reports.add(report);
        }
        return reports;
    }

    private ValidationReportFromImportingSchool validateSchool(SchoolRepresentation school, int index) {

        ValidationReportFromImportingSchool report = new ValidationReportFromImportingSchool(index, school.getRspo());
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

}
