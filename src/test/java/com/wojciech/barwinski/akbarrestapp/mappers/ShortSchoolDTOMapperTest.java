package com.wojciech.barwinski.akbarrestapp.mappers;


import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortSchoolDTOMapperTest {

    //TODO test dla mapowania obiektu z oraz bez numeru telefonu, oraz sprawdzenie mapowania

    private final ShortSchoolDTOMapper mapper = new ShortSchoolDTOMapper(new ModelMapper());

    @Test
    void shouldMapSchoolToShortSchoolDTOWithPhoneNumberAndAddress() {
        School schoolToMapping = getSchoolToMapping();
        ShortSchoolDTO expectedMapResult = getShortSchoolDTO();

        ShortSchoolDTO mapResult = mapper.mapSchoolToShortSchoolDTO(schoolToMapping);

        assertEquals(expectedMapResult, mapResult);
    }

    @Test
    void shouldMapSchoolWithNoPhoneToShortSchoolDTO(){
        School schoolToMapping = getSchoolWithNoPhoneNumber();

        String expectedPhone = "";

        ShortSchoolDTO mapResult = mapper.mapSchoolToShortSchoolDTO(schoolToMapping);

        assertEquals(expectedPhone, mapResult.getPhone());
    }

    private School getSchoolToMapping() {

        School school = new School(1234L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(new Phone.PhoneBuilder()
                .number("123456789")
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList.add(new Phone.PhoneBuilder()
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

    private ShortSchoolDTO getShortSchoolDTO() {
        ShortSchoolDTO schoolDTO = new ShortSchoolDTO();
        schoolDTO.setRspo(1234L);
        schoolDTO.setName("Central High School");
        schoolDTO.setVoivodeship(String.valueOf(Voivodeship.WARMINSKO_MAZURSKIE));
        schoolDTO.setCounty("Olsztyn County");
        schoolDTO.setBorough("Olsztyn");
        schoolDTO.setCity("Olsztyn");
        schoolDTO.setStreet("Słoneczna 15");
        schoolDTO.setPhone("123456789");

        return schoolDTO;
    }
}