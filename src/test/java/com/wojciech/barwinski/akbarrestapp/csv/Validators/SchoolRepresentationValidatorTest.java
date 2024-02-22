package com.wojciech.barwinski.akbarrestapp.csv.Validators;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.CsvSchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidateReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SchoolRepresentationValidatorTest {


    private final SchoolRepresentationValidator validator = new SchoolRepresentationValidator();

    @Test
    public void testSchoolsValidate() {
        List<CsvSchoolRepresentation> schools = createValidSchoolForTests();
        int expectedNumberOfValidSchool = 3;

        SchoolRepValidationResult result = validator.schoolsValidate(schools);
        List<CsvSchoolRepresentation> schoolsAfterValidate = result.getSchoolsAfterValidate();
        List<SchoolRepValidateReport> reports = result.getSchoolValidateReports();

        assertThat(schoolsAfterValidate.size()).isEqualTo(expectedNumberOfValidSchool);
        Assertions.assertThat(reports).noneSatisfy(report -> {
            Assertions.assertThat(report.getStatus()).isEqualTo(ValidationStatus.ERROR);
        });
    }

    @Test
    public void shouldValidateSchoolWithWarning(){
        List<CsvSchoolRepresentation> schools = createValidSchoolForTests();
        schools.get(0).setRspo("10");
        int expectedNumberOfValidSchool = 3;


        SchoolRepValidationResult result = validator.schoolsValidate(schools);
        List<CsvSchoolRepresentation> schoolsAfterValidate = result.getSchoolsAfterValidate();
        List<SchoolRepValidateReport> reports = result.getSchoolValidateReports();

        assertThat(schoolsAfterValidate.size()).isEqualTo(expectedNumberOfValidSchool);
        Assertions.assertThat(reports).anySatisfy(report -> {
            Assertions.assertThat(report.getStatus()).isEqualTo(ValidationStatus.WARNING);
        });
    }

    @Test
    public void shouldValidateOnlyCorrectSchoolAndGetErrorInReport(){
        List<CsvSchoolRepresentation> schools = createValidSchoolForTests();
        schools.get(0).setRspo("");
        int expectedNumberOfValidSchool = 2;


        SchoolRepValidationResult result = validator.schoolsValidate(schools);
        List<CsvSchoolRepresentation> schoolsAfterValidate = result.getSchoolsAfterValidate();
        List<SchoolRepValidateReport> reports = result.getSchoolValidateReports();

        assertThat(schoolsAfterValidate.size()).isEqualTo(expectedNumberOfValidSchool);
        Assertions.assertThat(reports).anySatisfy(report -> {
            Assertions.assertThat(report.getStatus()).isEqualTo(ValidationStatus.ERROR);
        });

    }

    private List<CsvSchoolRepresentation> createValidSchoolForTests() {

        List<CsvSchoolRepresentation> schools = new ArrayList<>();

        CsvSchoolRepresentation school1 = CsvSchoolRepresentation.builder()
                .rspo("12345678")
                .type("Public")
                .name("ABC School")
                .street("XYZ Street")
                .buildingNumber("10")
                .localNumber("A")
                .zipCode("12-345")
                .city("Example City")
                .phone("123-456-7890")
                .email("abc@example.com")
                .website("http://www.abc-school.com")
                .voivodeship("Mazowieckie")
                .county("Sample County")
                .borough("Sample Borough")
                .status("Active")
                .build();
        schools.add(school1);

        CsvSchoolRepresentation school2 = CsvSchoolRepresentation.builder()
                .rspo("87654321")
                .type("Private")
                .name("XYZ Academy")
                .street("PQR Avenue")
                .buildingNumber("20")
                .localNumber("B")
                .zipCode("54-321")
                .city("Test City")
                .phone("987-654-3210")
                .email("xyz@example.com")
                .website("http://www.xyzacademy.com")
                .voivodeship("Śląskie")
                .county("Test County")
                .borough("Test Borough")
                .status("Inactive")
                .build();
        schools.add(school2);

        CsvSchoolRepresentation school3 = CsvSchoolRepresentation.builder()
                .rspo("11112222")
                .type("Public")
                .name("PQR High School")
                .street("LMN Road")
                .buildingNumber("30")
                .localNumber("C")
                .zipCode("67-890")
                .city("Sampleville")
                .phone("111-222-3333")
                .email("pqr@example.com")
                .website("http://www.pqrhighschool.com")
                .voivodeship("Małopolskie")
                .county("Sample County")
                .borough("Another Borough")
                .status("Active")
                .build();
        schools.add(school3);

        return schools;
    }

}