package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FieldsCheckerTest {

    private final String specialSigns = "field contains special characters or symbols";
    private final String untypicalSize = "field has untypical size";
    private final String empty = "field is empty";
    private final String ok = "OK";
    private final String wrongFormat = "field has wrong format";
    private final String untypicalField = "field has untypical format";


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
    public void shouldCheckNoneRPSO_ERROR() {
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
    public void shouldCheckNoneSchoolType_ERROR() {
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
    public void shouldCheckNoneSchoolName_ERROR() {
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
    public void shouldCheckNoneSchoolStreet_ERROR() {
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
    public void shouldCheckNoneSchoolBuildingNumber_ERROR() {
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
    public void shouldCheckNoneSchoolLocalNumber_WARNING() {
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
    public void shouldCheckNoneSchoolZipCode_ERROR() {
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
    public void shouldCheckNoneSchoolPhoneNumber_ERROR() {
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

    //City
    @Test
    public void shouldCheckCorrectCity_OK() {
        String cityName = "Łódź";
        String expectedFieldName = "City";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolCity(cityName);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortCity_WARNING() {
        String cityName = "abc";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolCity(cityName);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckCityWithSpecialCharacters_WARNING() {
        String cityName = "Ł@d&";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolCity(cityName);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNoneCity_ERROR() {
        String cityName = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolCity(cityName);
        FieldReport nullReport = FieldsChecker.checkSchoolCity(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //Email
    @Test
    public void shouldCheckCorrectEmail_OK(){
        String email = "kontakt@sp000.lodz.edu.pl";
        String expectedFieldName = "Email";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolEmail(email);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckNoneEmail_OK(){
        String email = "";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport emptyReport = FieldsChecker.checkSchoolEmail(email);
        FieldReport nullReport = FieldsChecker.checkSchoolEmail(null);

        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckIncorrectEmail_OK(){
        String email1 = "absd";
        String email2 = "@@absd$";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report1 = FieldsChecker.checkSchoolEmail(email1);
        FieldReport report2 = FieldsChecker.checkSchoolEmail(email2);

        assertThat(report1.getComment()).isEqualTo(untypicalField);
        assertThat(report1.getStatus()).isEqualTo(expectedStatus);
        assertThat(report2.getComment()).isEqualTo(untypicalField);
        assertThat(report2.getStatus()).isEqualTo(expectedStatus);

    }

    //Website
    @Test
    public void shouldCheckCorrectWebsite_OK(){
        String website1 = "https://website.com";
        String website2 = "ftp://website.com";
        String website3 = "www.website.com";

        String expectedFieldName = "Website";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report1 = FieldsChecker.checkSchoolWebsite(website1);
        FieldReport report2 = FieldsChecker.checkSchoolWebsite(website2);
        FieldReport report3 = FieldsChecker.checkSchoolWebsite(website3);


        assertThat(report1.getComment()).isEqualTo(ok);
        assertThat(report2.getComment()).isEqualTo(ok);
        assertThat(report3.getComment()).isEqualTo(ok);
        assertThat(report1.getStatus()).isEqualTo(expectedStatus);
        assertThat(report1.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckIncorrectWebsite_OK(){
        String website1 = "website.com";
        String website2 = "ftp://website.comanama";
        String website3 = "www.website";

        String expectedFieldName = "Website";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report1 = FieldsChecker.checkSchoolWebsite(website1);
        FieldReport report2 = FieldsChecker.checkSchoolWebsite(website2);
        FieldReport report3 = FieldsChecker.checkSchoolWebsite(website3);


        assertThat(report1.getComment()).isEqualTo(untypicalField);
        assertThat(report2.getComment()).isEqualTo(untypicalField);
        assertThat(report3.getComment()).isEqualTo(untypicalField);
        assertThat(report1.getStatus()).isEqualTo(expectedStatus);
        assertThat(report1.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckNoneWebsite_OK(){
        String website = "";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport emptyReport = FieldsChecker.checkSchoolEmail(website);
        FieldReport nullReport = FieldsChecker.checkSchoolEmail(null);

        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);
    }

    //Voivodeship
    @Test
    public void shouldCheckCorrectVoivodeship_OK() {
        String voivodeship = "Łódzkie";
        String expectedFieldName = "Voivodeship";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolVoivodeship(voivodeship);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortVoivodeship_WARNING() {
        String voivodeship = "Łód";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolVoivodeship(voivodeship);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckVoivodeshipWithSpecialCharacters_WARNING() {
        String voivodeship = "Ł@d&";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolVoivodeship(voivodeship);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNoneVoivodeship_ERROR() {
        String voivodeship = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolVoivodeship(voivodeship);
        FieldReport nullReport = FieldsChecker.checkSchoolVoivodeship(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //County
    @Test
    public void shouldCheckCorrectCounty_OK() {
        String county = "Sieradzkie";
        String expectedFieldName = "County";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolCounty(county);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortCounty_WARNING() {
        String county = "Sie";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolCounty(county);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckCountyWithSpecialCharacters_WARNING() {
        String county = "Si#";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolCounty(county);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNoneCounty_ERROR() {
        String county = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolCounty(county);
        FieldReport nullReport = FieldsChecker.checkSchoolCounty(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //Borough
    @Test
    public void shouldCheckCorrectBorough_OK() {
        String borough = "Łódź - delegaruta";
        String expectedFieldName = "Borough";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolBorough(borough);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortBorough_WARNING() {
        String borough = "Łod";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolBorough(borough);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckBoroughWithSpecialCharacters_WARNING() {
        String borough = "Lo$";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolBorough(borough);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNoneBorough_ERROR() {
        String borough = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolBorough(borough);
        FieldReport nullReport = FieldsChecker.checkSchoolBorough(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

    //Status
    @Test
    public void shouldCheckCorrectStatus_OK() {
        String schoolStatus = "publiczna";
        String expectedFieldName = "School status";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolStatus(schoolStatus);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortStatus_WARNING() {
        String schoolStatus = "abc";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolStatus(schoolStatus);

        assertThat(report.getComment()).isEqualTo(untypicalSize);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckStatusWithSpecialCharacters_WARNING() {
        String schoolStatus = "Ł@d&";
        ValidationStatus expectedStatus = ValidationStatus.WARNING;

        FieldReport report = FieldsChecker.checkSchoolStatus(schoolStatus);

        assertThat(report.getComment()).isEqualTo(specialSigns);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNoneStatus_ERROR() {
        String schoolStatus = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolStatus(schoolStatus);
        FieldReport nullReport = FieldsChecker.checkSchoolStatus(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }

}