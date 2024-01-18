package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FieldsCheckerTest {

    private final String specialSigns = "field contains special characters or symbols";
    private final String untypicalSize = "field has untypical size";
    private final String empty = "field is empty";
    private final String ok = "OK";
    private final String wrongFormat = "field has wrong format";


    //RSPO
    @Test
    public void shouldCheckCorrectRSPO_OK() {
        String rspo = "12345";
        String expectedFieldName = "RSPO";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckLeadingTrailingSpacesRSPO_OK() {
        String rspo = " 123456 ";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckTooLongAndTooShortRPSO_WARNING() {
        String shortRSPO = "12";
        String longRSPO = "123456789";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport shortReport = FieldsChecker.checkRSPO(shortRSPO);
        FieldReport longReport = FieldsChecker.checkRSPO(longRSPO);

        assertThat(shortReport.getComment()).isEqualTo(untypicalSize);
        assertThat(shortReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(longReport.getStatus()).isEqualTo(expectedStatus);

    }

    @Test
    public void shouldCheckBlankRPSO_ERROR() {
        String rspo = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getComment()).isEqualTo(empty);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckRPSOThatContainLetters_ERROR() {
        String rspo = "123ab";

        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getComment()).isEqualTo("RSPO has non-digit signs");
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNullRPSO_ERROR() {
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(null);

        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSpecialCharacterRSPO_ERROR() {
        String rspo = "1234@5";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    //SchoolType
    @Test
    public void shouldCheckCorrectSchoolType_OK() {
        String schoolType = "Szkoła Podstawowa";
        String expectedFieldName = "School Type";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolType(schoolType);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortSchoolType_WARNING() {
        String schoolType = "abc";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolType(schoolType);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSchoolTypeWithSpecialCharacters_WARNING() {
        String schoolType = "Szkoła Podstawowa #1";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolType(schoolType);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectSchoolType_ERROR() {
        String schoolType = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolType(schoolType);
        FieldReport nullReport = FieldsChecker.checkSchoolType(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //SchoolName
    @Test
    public void shouldCheckCorrectSchoolName_OK() {
        String schoolName = "Szkoła Podstawowa nr 112 im. Jana Kochanowskiego";
        String expectedFieldName = "School Name";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolName(schoolName);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortSchoolName_WARNING() {
        String schoolName = "abc";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolName(schoolName);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSchoolNameWithSpecialCharacters_WARNING() {
        String schoolName = "Szkoła Podstawow@ nr. #1";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolName(schoolName);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectSchoolName_ERROR() {
        String schoolName = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolName(schoolName);
        FieldReport nullReport = FieldsChecker.checkSchoolName(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //SchoolStreet
    @Test
    public void shouldCheckCorrectSchoolStreet_OK() {
        String schoolStreet = "XYZ Street";
        String expectedFieldName = "School street";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolStreet(schoolStreet);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortSchoolStreet_WARNING() {
        String schoolStreet = "abc";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolStreet(schoolStreet);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSchoolStreetWithSpecialCharacters_WARNING() {
        String schoolStreet = "XYZ @*";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolStreet(schoolStreet);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectSchoolStreet_ERROR() {
        String schoolStreet = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolStreet(schoolStreet);
        FieldReport nullReport = FieldsChecker.checkSchoolStreet(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //SchoolBuildingNumber
    @Test
    public void shouldCheckCorrectSchoolBuildingNumber_OK() {
        String buildNumber = "12";
        String expectedFieldName = "Building Number";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolBuildingNumber(buildNumber);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckTooLongSchoolBuildingNumber_WARNING() {
        String buildNumber = "12345";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolBuildingNumber(buildNumber);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSchoolBuildingNumberWithSpecialCharacters_WARNING() {
        String buildNumber = "12$";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolBuildingNumber(buildNumber);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectSchoolBuildingNumber_ERROR() {
        String buildNumber = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolBuildingNumber(buildNumber);
        FieldReport nullReport = FieldsChecker.checkSchoolBuildingNumber(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //LocalNumber
    @Test
    public void shouldCheckCorrectSchoolLocalNumber_OK() {
        String localNumber = "12";
        String expectedFieldName = "Local number";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolLocalNumber(localNumber);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckTooLongSchoolLocalNumber_WARNING() {
        String localNumber = "12345";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolLocalNumber(localNumber);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSchoolLocalNumberWithSpecialCharacters_WARNING() {
        String localNumber = "12$";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolLocalNumber(localNumber);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectSchoolLocalNumber_WARNING() {
        String localNumber = "";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport emptyReport = FieldsChecker.checkSchoolLocalNumber(localNumber);
        FieldReport nullReport = FieldsChecker.checkSchoolLocalNumber(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //ZipCode
    @Test
    public void shouldCheckCorrectSchoolZipCode_OK() {
        String zipCode1 = "12-345";
        String zipCode2 = "12345";
        String expectedFieldName = "Zip code";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report1 = FieldsChecker.checkSchoolZipCode(zipCode1);
        FieldReport report2 = FieldsChecker.checkSchoolZipCode(zipCode2);

        assertThat(report1.getComment()).isEqualTo(ok);
        assertThat(report2.getComment()).isEqualTo(ok);
        assertThat(report1.getStatus()).isEqualTo(expectedStatus);
        assertThat(report1.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckIncorrectSchoolZipCode_ERROR() {
        String zipCode1 = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolZipCode(zipCode1);
        FieldReport nullReport = FieldsChecker.checkSchoolZipCode(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    @Test
    public void shouldCheckWrongFormatSchoolZipCode_ERROR() {
        String zipCode = "123-45";
        String expectedFieldName = "Zip code";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkSchoolZipCode(zipCode);

        assertThat(report.getComment()).isEqualTo(wrongFormat);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckWrongLongSchoolZipCode_ERROR() {
        String zipCode = "123456";
        String expectedFieldName = "Zip code";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkSchoolZipCode(zipCode);

        assertThat(report.getComment()).isEqualTo(wrongFormat);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckSchoolZipCodeWithSpecialSigns_ERROR() {
        String zipCode = "12-@45";
        String expectedFieldName = "Zip code";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkSchoolZipCode(zipCode);

        assertThat(report.getComment()).isEqualTo(wrongFormat);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckSchoolZipCodeWithLetters_ERROR() {
        String zipCode = "12-abc";
        String expectedFieldName = "Zip code";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkSchoolZipCode(zipCode);

        assertThat(report.getComment()).isEqualTo(wrongFormat);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    //Phone
    @Test
    public void shouldCheckCorrectSchoolPhoneNumber_OK() {
        String phone1 = "111-222-333";
        String phone2 = "42 123-45-67";
        String phone3 = "42 1234567";

        String expectedFieldName = "Phone";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report1 = FieldsChecker.checkSchoolPhone(phone1);
        FieldReport report2 = FieldsChecker.checkSchoolPhone(phone2);
        FieldReport report3 = FieldsChecker.checkSchoolPhone(phone3);


        assertThat(report1.getComment()).isEqualTo(ok);
        assertThat(report2.getComment()).isEqualTo(ok);
        assertThat(report3.getComment()).isEqualTo(ok);
        assertThat(report1.getStatus()).isEqualTo(expectedStatus);
        assertThat(report1.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckTooLongAndTooShortPhoneNumber_WARNING() {
        String shortPhone = "12";
        String longPhone = "1234567890123";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport shortReport = FieldsChecker.checkRSPO(shortPhone);
        FieldReport longReport = FieldsChecker.checkRSPO(longPhone);

        assertThat(shortReport.getComment()).isEqualTo(untypicalSize);
        assertThat(shortReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(longReport.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectSchoolPhoneNumber_ERROR() {
        String phone = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolPhone(phone);
        FieldReport nullReport = FieldsChecker.checkSchoolPhone(null);

        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    @Test
    public void shouldCheckStringWithLetterAndSpecialSignsSchoolPhoneNumber_ERROR() {
        String phone1 = "111-aaa-333";
        String phone2 = "111-@#$-333";

        String expectedFieldName = "Phone";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report1 = FieldsChecker.checkSchoolPhone(phone1);
        FieldReport report2 = FieldsChecker.checkSchoolPhone(phone2);


        assertThat(report1.getComment()).isEqualTo(specialSigns);
        assertThat(report2.getComment()).isEqualTo(specialSigns);
        assertThat(report1.getStatus()).isEqualTo(expectedStatus);
        assertThat(report1.getFieldName()).isEqualTo(expectedFieldName);
    }


}