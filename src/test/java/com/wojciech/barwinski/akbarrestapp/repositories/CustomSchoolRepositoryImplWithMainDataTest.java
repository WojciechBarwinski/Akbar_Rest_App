package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest()
class CustomSchoolRepositoryImplWithMainDataTest {

    @Autowired
    private SchoolRepository repository;

    @BeforeAll
    void createDataForTests() {
        repository.saveAll(schoolsToTest());
    }


    @Test
    void shouldSearchRequestOnlyBySchoolType() {
        String schoolTypeToSearch = "High School";
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .type(schoolTypeToSearch)
                .build();
        int expectedNumberOfSchools = 2;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        assertEquals("Another High School", schoolBySearchRequest.get(1).getName());
    }

    @Test
    void shouldSearchRequestOnlyBySchoolName() {
        String schoolNameToSearch = "High School";
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .schoolName(schoolNameToSearch)
                .build();
        int expectedNumberOfSchools = 2;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        assertEquals("Another High School", schoolBySearchRequest.get(1).getName());
    }

    @Transactional
    @Test
    void shouldSearchRequestBySchoolNameAndPhone() {
        String schoolNameToSearch = "High School";
        String schoolPhoneSearch = "123456789";
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .schoolName(schoolNameToSearch)
                .phoneNumber(schoolPhoneSearch)
                .build();
        int expectedNumberOfSchools = 1;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        assertEquals(schoolPhoneSearch, schoolBySearchRequest.get(0).getPhone());
    }

    @Test
    void shouldSearchRequestOnlyBySchoolEmail() {
        String schoolEmailToSearch = "info@centralhigh.edu";
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .email(schoolEmailToSearch)
                .build();
        int expectedNumberOfSchools = 1;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
    }

    @Transactional
    @Test
    void shouldSearchRequestOnlyByAllMainData() {
        String schoolTypeToSearch = "High School";
        String schoolNameToSearch = "High School";
        String schoolEmailToSearch = "info@centralhigh.edu";
        String schoolPhoneSearch = "123456789";

        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .type(schoolTypeToSearch)
                .schoolName(schoolNameToSearch)
                .email(schoolEmailToSearch)
                .phoneNumber(schoolPhoneSearch)
                .build();

        int expectedNumberOfSchools = 1;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        //assertEquals(schoolEmailToSearch, schoolBySearchRequest.get(0).ge);
        assertEquals(schoolPhoneSearch, schoolBySearchRequest.get(0).getPhone());
    }



    private List<School> schoolsToTest() {
        List<School> schools = new ArrayList<>();
        School school1 = new School(1L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        School school2 = new School(2L, "Middle School", "Washington Middle School", "info@washingtonmiddle.edu", "www.washingtonmiddle.edu", "Public",
                new Address(Voivodeship.LUBELSKIE, "Lublin County", "Lublin", "Lublin", "Lipowa 5", "20-002"));

        School school3 = new School(3L, "High School", "Another High School", "info@anothercentralhigh.edu", "www.centralhigh.edu", "Public",
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
        school1.setPhones(phoneList);

        AdditionalSchoolInformation addInfo = new AdditionalSchoolInformation(
                new Status(true, true, false, false, "notka statusu"),
                new Notation("note1", "note2", "note3"),
                new Schedule(LocalDate.of(2022, 10, 15),
                        LocalDate.of(2022, 10, 20),
                        LocalDate.of(2022, 11, 1),
                        3,
                        LocalDate.of(2022, 11, 15),
                        LocalDate.of(2022, 11, 10),
                        "Schedule for client John Doe"));

        school1.setAdditionalSchoolInformation(addInfo);

        schools.add(school1);
        schools.add(school2);
        schools.add(school3);

        return schools;
    }
}