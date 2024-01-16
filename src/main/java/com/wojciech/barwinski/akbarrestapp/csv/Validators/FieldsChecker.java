package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;

import static com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus.ERROR;
import static com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus.WARNING;

public class FieldsChecker {

    public static FieldReport checkRSPO(String rspo) {
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

    public static FieldReport checkSchoolType(String schoolType) {
        return checkBlankAndStringLength(schoolType, new FieldReport("School Type"));
    }

    public static FieldReport checkSchoolName(String schoolName) {
        return checkBlankAndStringLength(schoolName, new FieldReport("School Name"));
    }

    public static FieldReport checkSchoolStreet(String street) {
        return checkBlankAndStringLength(street, new FieldReport("School street"));
    }

    public static FieldReport checkSchoolBuildingNumber(String buildingNumber) {
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

    public static FieldReport checkSchoolLocalNumber(String localNumber) {
        FieldReport fieldReport = new FieldReport("Local number");
        if (localNumber.isBlank()) {
            fieldReport.setStatus(WARNING);
            fieldReport.setComment("There is no local number");
        }

        return fieldReport;
    }

    public static FieldReport checkSchoolZipCode(String zipCode) {
        FieldReport fieldReport = new FieldReport("Zip code");

        if (zipCode.isBlank() || !(zipCode.matches("\\d{2}-\\d{3}") || zipCode.matches("\\d{5}"))){
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("zip code is null or have wrong format");
        }
        return fieldReport;
    }

    public static FieldReport checkSchoolCity(String city) {
        return checkBlankAndStringLength(city, new FieldReport("City"));
    }

    public static FieldReport checkSchoolPhone(String phone) {
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

    public static FieldReport checkSchoolEmail(String email) {
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

    public static FieldReport checkSchoolWebsite(String website) {
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

    public static FieldReport checkSchoolVoivodeship(String voivodeship) {
        return checkBlankAndStringLength(voivodeship, new FieldReport("Voivodeship"));
    }

    public static FieldReport checkSchoolCounty(String county) {
        return checkBlankAndStringLength(county, new FieldReport("County"));
    }

    public static FieldReport checkSchoolBorough(String borough) {
        return checkBlankAndStringLength(borough, new FieldReport("Borough"));
    }

    public static FieldReport checkSchoolStatus(String status) {
        return checkBlankAndStringLength(status, new FieldReport("School status"));
    }

    private static FieldReport checkBlankAndStringLength(String field, FieldReport fieldReport) {

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
