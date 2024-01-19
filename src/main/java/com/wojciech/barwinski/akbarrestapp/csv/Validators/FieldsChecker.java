package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;


import static com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus.ERROR;
import static com.wojciech.barwinski.akbarrestapp.csv.Validators.ValidationStatus.WARNING;

public class FieldsChecker {

    private static final String specialSigns = "field contains special characters or symbols";
    private static final String untypicalSize = "field has untypical size";
    private static final String emptyField = "field is empty";
    private static final String untypicalField = "field has untypical format";
    private static final String specialSignsRegex = ".*[\\p{P}\\p{S}&&[^.-]]+.*";

    public static FieldReport checkRSPO(String rspo) {
        FieldReport fieldReport = new FieldReport("RSPO");
        if (rspo == null || rspo.isBlank()){
            return setEmptyFieldERROR(fieldReport);
        }else if (rspo.matches(".*[a-zA-Z\\p{P}\\p{S}].*")){
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("RSPO has non-digit signs");
            return fieldReport;
        } else if (!rspo.trim().matches("\\d{3,8}")) {
            return setUntypicalSizeWARNING(fieldReport);
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
        FieldReport fieldReport = new FieldReport("Building Number");
        if (buildingNumber == null || buildingNumber.isBlank()){
            return setEmptyFieldERROR(fieldReport);
        } else if (buildingNumber.trim().length() > 4) {
            return setUntypicalSizeWARNING(fieldReport);
        } else if (buildingNumber.matches(specialSignsRegex)) {
            return setUntypicalSpecialSignsWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    public static FieldReport checkSchoolLocalNumber(String localNumber) {
        FieldReport fieldReport = new FieldReport("Local number");
        if (localNumber == null || localNumber.isBlank()){
            return setEmptyFieldWARNING(fieldReport);
        } else if (localNumber.matches(specialSignsRegex)) {
            return setUntypicalSpecialSignsWARNING(fieldReport);
        }else if (localNumber.trim().length() > 4) {
            return setUntypicalSizeWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    public static FieldReport checkSchoolZipCode(String zipCode) {
        FieldReport fieldReport = new FieldReport("Zip code");
        if (zipCode == null || zipCode.isBlank()){
            return setEmptyFieldERROR(fieldReport);
        } else if (!(zipCode.matches("\\d{2}-\\d{3}") || zipCode.matches("\\d{5}"))){
            fieldReport.setStatus(ERROR);
            fieldReport.setComment("field has wrong format");
        }
        return fieldReport;
    }

    public static FieldReport checkSchoolCity(String city) {
        return checkBlankAndStringLength(city, new FieldReport("City"));
    }

    public static FieldReport checkSchoolPhone(String phone) {
        FieldReport fieldReport = new FieldReport("Phone");
        if (phone == null || phone.isBlank()){
            return setEmptyFieldERROR(fieldReport);
        } else if (phone.replace("-", "").trim().matches(".*[a-zA-Z\\p{P}\\p{S}].*")){
            return setUntypicalSpecialSignsERROR(fieldReport);
        } else if (!phone.replace("-", "").trim().matches(".*\\d{5,11}.*")) {
            return setUntypicalSizeWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    public static FieldReport checkSchoolEmail(String email) {
        FieldReport fieldReport = new FieldReport("Email");
        if (email == null || email.isBlank()){
            return setEmptyFieldWARNING(fieldReport);
        }else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")){
            return setUntypicalFormatWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    public static FieldReport checkSchoolWebsite(String website) {
        FieldReport fieldReport = new FieldReport("Website");
        if (website == null || website.isBlank()) {
            return setEmptyFieldWARNING(fieldReport);
        } else if (!website.matches("^(https?://|ftp://|www)[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}(:\\d+)?(/\\S*)?$")) {
            return setUntypicalFormatWARNING(fieldReport);
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

    private static FieldReport checkBlankAndStringLength(String field, FieldReport report) {

        if (field == null || field.isBlank()){
            return setEmptyFieldERROR(report);
        } else if (field.matches(specialSignsRegex)) {
            return setUntypicalSpecialSignsWARNING(report);
        }else if (field.trim().length() < 4) {
            return setUntypicalSizeWARNING(report);
        } else {
            return report;
        }
    }

    private static FieldReport setEmptyFieldERROR(FieldReport fieldReport){
        fieldReport.setStatus(ERROR);
        fieldReport.setComment(emptyField);
        return fieldReport;
    }

    private static FieldReport setEmptyFieldWARNING(FieldReport fieldReport){
        fieldReport.setStatus(WARNING);
        fieldReport.setComment(emptyField);
        return fieldReport;
    }

    private static FieldReport setUntypicalSizeWARNING(FieldReport report){
        report.setStatus(WARNING);
        report.setComment(untypicalSize);
        return report;
    }

    private static FieldReport setUntypicalFormatWARNING(FieldReport report){
        report.setStatus(WARNING);
        report.setComment(untypicalField);
        return report;
    }

    private static FieldReport setUntypicalSpecialSignsERROR(FieldReport report){
        report.setStatus(ERROR);
        report.setComment(specialSigns);
        return report;
    }

    private static FieldReport setUntypicalSpecialSignsWARNING(FieldReport report){
        report.setStatus(WARNING);
        report.setComment(specialSigns);
        return report;
    }

}
