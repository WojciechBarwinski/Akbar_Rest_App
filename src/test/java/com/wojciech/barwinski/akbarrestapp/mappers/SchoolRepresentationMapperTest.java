package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SchoolRepresentationMapperTest {

    SchoolRepresentationMapper mapper = new SchoolRepresentationMapper(new ModelMapper());

    /*
     * These test assume only SchoolRepresentation obj that pass validation stage
     * */

    @Test
    void shouldMapCorrectSchoolRepresentationFromCSVFile() {
        SchoolRepresentation schoolFromCsv = SchoolRepresentation.builder()
                .rspo("123456")
                .type("Public")
                .name("ABC School")
                .street("XYZ Street")
                .buildingNumber("10")
                .localNumber("")
                .zipCode("12-345")
                .city("Example City")
                .phone("123-456-7890")
                .email("")
                .website("")
                .voivodeship("Mazowieckie")
                .county("Sample County")
                .borough("Sample Borough")
                .status("Active")
                .build();

        int rspo = 123456;
        School school = mapper.mapSchoolRepresentationToSchool(schoolFromCsv);

        assertNotNull(school);
        assertEquals(schoolFromCsv.getName(), school.getName());
        assertEquals(schoolFromCsv.getPhone(), school.getPhones().get(0).getNumber());
        assertEquals(schoolFromCsv.getVoivodeship(), school.getAddress().getVoivodeship().getName());
        assertEquals(rspo, school.getRspo());
        assertEquals(schoolFromCsv.getEmail(), school.getEmail());
    }

    @Test
    void shouldMapCorrectSchoolRepresentationFromJson() {
        SchoolRepresentation schoolFromCsv = SchoolRepresentation.builder()
                .rspo("123456")
                .type("Public")
                .name("ABC School")
                .street("XYZ Street")
                .buildingNumber("10")
                .localNumber("")
                .zipCode("12-345")
                .city("Example City")
                .phone("123-456-7890")
                .email(null)
                .website(null)
                .voivodeship("Mazowieckie")
                .county("Sample County")
                .borough("Sample Borough")
                .status("Active")
                .build();

        int rspo = 123456;
        School school = mapper.mapSchoolRepresentationToSchool(schoolFromCsv);

        assertNotNull(school);
        assertEquals(schoolFromCsv.getName(), school.getName());
        assertEquals(schoolFromCsv.getPhone(), school.getPhones().get(0).getNumber());
        assertEquals(schoolFromCsv.getVoivodeship(), school.getAddress().getVoivodeship().getName());
        assertEquals(rspo, school.getRspo());
        assertEquals(schoolFromCsv.getEmail(), school.getEmail());

    }

}