package com.wojciech.barwinski.akbarrestapp.validator.toUpload;

import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.FieldReportDTO;

class FieldsChecker {

    private static final String specialSigns = "field contains special characters or symbols";
    private static final String untypicalSize = "field has untypical size";
    private static final String emptyField = "field is empty";
    private static final String untypicalField = "field has untypical format";
    private static final String specialSignsRegex = ".*[\\p{P}\\p{S}&&[^.-]]+.*";

    static FieldReportDTO checkRSPO(String rspo) {
        FieldReportDTO fieldReport = new FieldReportDTO("RSPO");
        if (rspo == null || rspo.isBlank()){
            return setEmptyFieldERROR(fieldReport);
        }else if (rspo.matches(".*[a-zA-Z\\p{P}\\p{S}].*")){
            fieldReport.setStatus(ValidationStatus.ERROR);
            fieldReport.setComment("RSPO has non-digit signs");
            return fieldReport;
        } else if (!rspo.trim().matches("\\d{3,8}")) {
            return setUntypicalSizeWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    static FieldReportDTO checkSchoolType(String schoolType) {
        return checkBlankAndStringLength(schoolType, new FieldReportDTO("School Type"));
    }

    static FieldReportDTO checkSchoolName(String schoolName) {
        return checkBlankAndStringLength(schoolName, new FieldReportDTO("School Name"));
    }

    static FieldReportDTO checkSchoolStreet(String street) {
        return checkBlankAndStringLength(street, new FieldReportDTO("School street"));
    }

    static FieldReportDTO checkSchoolBuildingNumber(String buildingNumber) {
        FieldReportDTO fieldReport = new FieldReportDTO("Building Number");
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

    static FieldReportDTO checkSchoolLocalNumber(String localNumber) {
        FieldReportDTO fieldReport = new FieldReportDTO("Local number");
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

    static FieldReportDTO checkSchoolZipCode(String zipCode) {
        FieldReportDTO fieldReport = new FieldReportDTO("Zip code");
        if (zipCode == null || zipCode.isBlank()){
            return setEmptyFieldERROR(fieldReport);
        } else if (!zipCode.trim().matches("\\d{2}-\\d{3}")){
            fieldReport.setStatus(ValidationStatus.ERROR);
            fieldReport.setComment("field has wrong format");
        }
        return fieldReport;
    }

    static FieldReportDTO checkSchoolCity(String city) {
        return checkBlankAndStringLength(city, new FieldReportDTO("City"));
    }

    static FieldReportDTO checkSchoolPhone(String phone) {
        FieldReportDTO fieldReport = new FieldReportDTO("Phone");
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

    static FieldReportDTO checkSchoolEmail(String email) {
        FieldReportDTO fieldReport = new FieldReportDTO("Email");
        if (email == null || email.isBlank()){
            return setEmptyFieldWARNING(fieldReport);
        }else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")){
            return setUntypicalFormatWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    static FieldReportDTO checkSchoolWebsite(String website) {
        FieldReportDTO fieldReport = new FieldReportDTO("Website");
        if (website == null || website.isBlank()) {
            return setEmptyFieldWARNING(fieldReport);
        } else if (!website.matches("^(https?://|ftp://|www)[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}(:\\d+)?(/\\S*)?$")) {
            return setUntypicalFormatWARNING(fieldReport);
        } else {
            return fieldReport;
        }
    }

    static FieldReportDTO checkSchoolVoivodeship(String voivodeship) {
        return checkBlankAndStringLength(voivodeship, new FieldReportDTO("Voivodeship"));
    }

    static FieldReportDTO checkSchoolCounty(String county) {
        return checkBlankAndStringLength(county, new FieldReportDTO("County"));
    }

    static FieldReportDTO checkSchoolBorough(String borough) {
        return checkBlankAndStringLength(borough, new FieldReportDTO("Borough"));
    }

    static FieldReportDTO checkSchoolStatus(String status) {
        return checkBlankAndStringLength(status, new FieldReportDTO("School status"));
    }

    static FieldReportDTO checkBlankAndStringLength(String field, FieldReportDTO report) {

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

    static FieldReportDTO setEmptyFieldERROR(FieldReportDTO fieldReport){
        fieldReport.setStatus(ValidationStatus.ERROR);
        fieldReport.setComment(emptyField);
        return fieldReport;
    }

    static FieldReportDTO setEmptyFieldWARNING(FieldReportDTO fieldReport){
        fieldReport.setStatus(ValidationStatus.WARNING);
        fieldReport.setComment(emptyField);
        return fieldReport;
    }

    static FieldReportDTO setUntypicalSizeWARNING(FieldReportDTO report){
        report.setStatus(ValidationStatus.WARNING);
        report.setComment(untypicalSize);
        return report;
    }

    static FieldReportDTO setUntypicalFormatWARNING(FieldReportDTO report){
        report.setStatus(ValidationStatus.WARNING);
        report.setComment(untypicalField);
        return report;
    }

    static FieldReportDTO setUntypicalSpecialSignsERROR(FieldReportDTO report){
        report.setStatus(ValidationStatus.ERROR);
        report.setComment(specialSigns);
        return report;
    }

    static FieldReportDTO setUntypicalSpecialSignsWARNING(FieldReportDTO report){
        report.setStatus(ValidationStatus.WARNING);
        report.setComment(specialSigns);
        return report;
    }
}
