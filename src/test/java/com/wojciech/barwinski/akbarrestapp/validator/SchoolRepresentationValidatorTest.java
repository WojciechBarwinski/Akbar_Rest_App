package com.wojciech.barwinski.akbarrestapp.validator;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.CsvSchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromSchoolImportDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SchoolRepresentationValidatorTest {


    private final SchoolRepresentationValidator validator = new SchoolRepresentationValidator();

    @Test
    public void testSchoolsValidate() {
        List<SchoolRepresentation> schools = createValidSchoolForTests();
        int expectedNumberOfReports = 3;


        List<ValidationReportFromSchoolImportDTO> resultOfValidation = validator.schoolsValidate(schools);


        assertThat(resultOfValidation.size()).isEqualTo(expectedNumberOfReports);
        Assertions.assertThat(resultOfValidation).noneSatisfy(report -> {
            Assertions.assertThat(report.getStatus()).isEqualTo(ValidationStatus.ERROR);
        });
    }

    @Test
    public void shouldValidateSchoolWithWarning(){
        List<SchoolRepresentation> schools = createValidSchoolForTests();
        String rspoThatShouldReturnWarning = "10";
        schools.get(0).setRspo(rspoThatShouldReturnWarning);


        List<ValidationReportFromSchoolImportDTO> resultOfValidation = validator.schoolsValidate(schools);


        Assertions.assertThat(resultOfValidation).anySatisfy(report -> {
            Assertions.assertThat(report.getStatus()).isEqualTo(ValidationStatus.WARNING);
        });
    }

    @Test
    public void shouldValidateOnlyCorrectSchoolAndGetErrorInReport(){
        List<SchoolRepresentation> schools = createValidSchoolForTests();
        String rspoThatShouldReturnError = " ";
        schools.get(0).setRspo(rspoThatShouldReturnError);


        List<ValidationReportFromSchoolImportDTO> resultOfValidation = validator.schoolsValidate(schools);


        Assertions.assertThat(resultOfValidation).anySatisfy(report -> {
            Assertions.assertThat(report.getStatus()).isEqualTo(ValidationStatus.ERROR);
        });
    }

    @Test
    public void testEmptySchoolsList() {
        List<SchoolRepresentation> emptySchools = new ArrayList<>();
        int expectedNumberOfReports = 0;

        List<ValidationReportFromSchoolImportDTO> resultOfValidation = validator.schoolsValidate(emptySchools);

        assertThat(resultOfValidation.size()).isEqualTo(expectedNumberOfReports);
    }


    private List<SchoolRepresentation> createValidSchoolForTests() {

        List<SchoolRepresentation> schools = new ArrayList<>();

        SchoolRepresentation school1 = SchoolRepresentation.builder()
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

        SchoolRepresentation school2 = CsvSchoolRepresentation.builder()
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

        SchoolRepresentation school3 = CsvSchoolRepresentation.builder()
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