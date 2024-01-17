package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.FieldReport;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FieldsCheckerTest {

    private final String specialSigns = "field contains special characters or symbols";
    private final String untypicalSize = "field has untypical size";
    private final String empty = "field is empty";
    private final String ok = "OK";


    //RSPO
    @Test
    public void shouldCheckCorrectRSPO_OK(){
        String rspo = "12345";
        String expectedFieldName = "RSPO";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);
    }

    @Test
    public void shouldCheckLeadingTrailingSpacesRSPO_OK(){
        String rspo = " 123456 ";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckTooLongAndTooShortRPSO_WARNING(){
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
    public void shouldCheckBlankRPSO_ERROR(){
        String rspo = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getComment()).isEqualTo(empty);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckRPSOThatContainLetters_ERROR(){
        String rspo = "123ab";

        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getComment()).isEqualTo("RSPO has non-digit signs");
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckNullRPSO_ERROR(){
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(null);

        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldCheckSpecialCharacterRSPO_ERROR(){
        String rspo = "1234@5";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport report = FieldsChecker.checkRSPO(rspo);

        assertThat(report.getStatus()).isEqualTo(expectedStatus);
    }

    //SchoolType
    @Test
    public void shouldCheckCorrectSchoolType_OK(){
        String schoolType = "Szkoła Podstawowa";
        String expectedFieldName = "School Type";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolType(schoolType);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortSchoolType_WARNING(){
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
    public void shouldCheckIncorrectSchoolType_ERROR(){
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
    public void shouldCheckCorrectSchoolName_OK(){
        String schoolName = "Szkoła Podstawowa nr 112 im. Jana Kochanowskiego";
        String expectedFieldName = "School Name";
        ValidationStatus expectedStatus = ValidationStatus.OK;

        FieldReport report = FieldsChecker.checkSchoolName(schoolName);

        assertThat(report.getComment()).isEqualTo(ok);
        assertThat(report.getStatus()).isEqualTo(expectedStatus);
        assertThat(report.getFieldName()).isEqualTo(expectedFieldName);

    }

    @Test
    public void shouldCheckTooShortSchoolName_WARNING(){
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
    public void shouldCheckIncorrectSchoolName_ERROR(){
        String schoolName = "";
        ValidationStatus expectedStatus = ValidationStatus.ERROR;

        FieldReport emptyReport = FieldsChecker.checkSchoolName(schoolName);
        FieldReport nullReport = FieldsChecker.checkSchoolName(null);


        assertThat(emptyReport.getComment()).isEqualTo(empty);
        assertThat(emptyReport.getStatus()).isEqualTo(expectedStatus);
        assertThat(nullReport.getStatus()).isEqualTo(expectedStatus);

    }


}