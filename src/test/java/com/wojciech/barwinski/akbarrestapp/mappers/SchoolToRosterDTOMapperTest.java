package com.wojciech.barwinski.akbarrestapp.mappers;


import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchoolToRosterDTOMapperTest {

    //TODO test dla mapowania obiektu z oraz bez numeru telefonu, oraz sprawdzenie mapowania

    private final SchoolToRosterMapper mapper = new SchoolToRosterMapper(new ModelMapper());

    @Test
    void shouldMapSchoolToShortSchoolDTOWithPhoneNumberAndAddress() {
        School schoolToMapping = getSchoolToMapping();
        SchoolToRosterDTO expectedMapResult = getShortSchoolDTO();

        SchoolToRosterDTO mapResult = mapper.mapSchoolToSchoolToRosterDTO(schoolToMapping);

        assertEquals(expectedMapResult, mapResult);
    }

    @Test
    void shouldMapSchoolWithNoPhoneToShortSchoolDTO(){
        School schoolToMapping = getSchoolWithNoPhoneNumber();

        String expectedPhone = "";

        SchoolToRosterDTO mapResult = mapper.mapSchoolToSchoolToRosterDTO(schoolToMapping);

        assertEquals(expectedPhone, mapResult.getPhone());
    }

    private School getSchoolToMapping() {

        School school = new School(1234L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(Phone.builder()
                .number("123456789")
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList.add(Phone.builder()
                .number("987654321")
                .owner("Jane Doe")
                .phoneNote("Personal phone")
                .build());
        school.setPhones(phoneList);

        /*AdditionalSchoolInformation addInfo = new AdditionalSchoolInformation(
                new Status(true, true, false, false, "notka statusu"),
                new Notation("note1", "note2", "note3"),
                new Schedule(LocalDate.of(2022, 10, 15),
                        LocalDate.of(2022, 10, 20),
                        LocalDate.of(2022, 11, 1),
                        3,
                        LocalDate.of(2022, 11, 15),
                        LocalDate.of(2022, 11, 10),
                        "Schedule for client John Doe"));

        school.setAdditionalSchoolInformation(addInfo);*/

        return school;
    }

    private School getSchoolWithNoPhoneNumber(){
       return new School(1234L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));
    }

    private SchoolToRosterDTO getShortSchoolDTO() {
        SchoolToRosterDTO schoolDTO = new SchoolToRosterDTO();
        schoolDTO.setRspo(1234L);
        schoolDTO.setName("Central High School");
        schoolDTO.setVoivodeship(Voivodeship.WARMINSKO_MAZURSKIE);
        schoolDTO.setCounty("Olsztyn County");
        schoolDTO.setBorough("Olsztyn");
        schoolDTO.setCity("Olsztyn");
        schoolDTO.setStreet("Słoneczna 15");
        schoolDTO.setPhone("123456789");

        return schoolDTO;
    }
}