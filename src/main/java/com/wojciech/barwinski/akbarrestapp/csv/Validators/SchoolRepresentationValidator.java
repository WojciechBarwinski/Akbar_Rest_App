package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidateReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidationResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("------------------- report-----------");
        System.out.println(school.getName());
        System.out.println(report.getStatus());
        for (FieldReport field : fields) {
            System.out.println(field.getFieldName() + " : " + field.getStatus() + " : " + field.getComment());
        }

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

    private FieldReport checkRSPO(String rspo) {
        FieldReport fieldReport = new FieldReport("RSPO");

        if (rspo.isBlank() || rspo.matches(".*[a-zA-Z].*")) {
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("RSPO is null or have non-digit signs");
            return fieldReport;
        } else if (!rspo.matches(".*\\d{3,8}.*")) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("RSPO has untypical size");
            return fieldReport;
        } else {
            return fieldReport;
        }
    }

    private FieldReport checkSchoolType(String schoolType) {
        return checkBlankAndStringLength(schoolType, new FieldReport("School Type"));
    }

    private FieldReport checkSchoolName(String schoolName) {
        return checkBlankAndStringLength(schoolName, new FieldReport("School Name"));
    }

    private FieldReport checkSchoolStreet(String street) {
        return checkBlankAndStringLength(street, new FieldReport("School street"));
    }

    private FieldReport checkSchoolBuildingNumber(String buildingNumber) {
        FieldReport fieldReport = new FieldReport("Building number");

        if (buildingNumber.isBlank()){
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("building number is empty");
            return fieldReport;
        } else if (buildingNumber.trim().length() > 5) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("Building number has untypical size");
            return fieldReport;
        } else {
            return fieldReport;
        }

    }

    private FieldReport checkSchoolLocalNumber(String localNumber) {
        FieldReport fieldReport = new FieldReport("Local number");
        if (localNumber.isBlank()) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("There is no local number");
        }

        return fieldReport;
    }

    private FieldReport checkSchoolZipCode(String zipCode) {
        FieldReport fieldReport = new FieldReport("Zip code");

        if (zipCode.isBlank() || (zipCode.matches("\\d{2}-\\d{3}") || zipCode.matches("\\d{5}"))){
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("zip code is null or have wrong format");
        }
        return fieldReport;
    }

    private FieldReport checkSchoolCity(String city) {
        return checkBlankAndStringLength(city, new FieldReport("City"));
    }

    private FieldReport checkSchoolPhone(String phone) {
        FieldReport fieldReport = new FieldReport("Phone");
        if (phone.isBlank() || phone.replace("-", "").trim().matches(".*[a-zA-Z].*")){
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("Phone number is null or have non-digit signs");
            return fieldReport;
        } else if (!phone.replace("-", "").trim().matches(".*\\d{5,11}.*")) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("Phone number has untypical size");
            return fieldReport;
        } else {
            return fieldReport;
        }
    }

    private FieldReport checkSchoolEmail(String email) {
        FieldReport fieldReport = new FieldReport("Email");
        if (email.isBlank()){
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("there is no email");
            return fieldReport;
        }else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")){
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("email address has untypical format");
            return fieldReport;
        } else {
            return fieldReport;
        }
    }

    private FieldReport checkSchoolWebsite(String website) {
        FieldReport fieldReport = new FieldReport("Email");
        if (website.isBlank()) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("there is no website");
            return fieldReport;
        } else if (!website.matches("^(https?|ftp)://[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}(:\\d+)?(/\\S*)?$")) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("website address has untypical format");
            return fieldReport;
        } else {
            return fieldReport;
        }
    }

    private FieldReport checkSchoolVoivodeship(String voivodeship) {
        return checkBlankAndStringLength(voivodeship, new FieldReport("Voivodeship"));
    }

    private FieldReport checkSchoolCounty(String county) {
        return checkBlankAndStringLength(county, new FieldReport("County"));
    }

    private FieldReport checkSchoolBorough(String borough) {
        return checkBlankAndStringLength(borough, new FieldReport("Borough"));
    }

    private FieldReport checkSchoolStatus(String status) {
        return checkBlankAndStringLength(status, new FieldReport("School status"));
    }

    private FieldReport checkBlankAndStringLength(String field, FieldReport fieldReport) {

        if (field.isBlank()) {
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("field is empty");
            return fieldReport;
        } else if (field.trim().length() < 4) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("field has untypical size");
            return fieldReport;
        } else {
            return fieldReport;
        }
    }

}
