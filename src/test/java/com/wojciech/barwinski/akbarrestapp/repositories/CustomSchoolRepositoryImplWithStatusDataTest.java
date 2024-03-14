package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import com.wojciech.barwinski.akbarrestapp.mappers.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest()
public class CustomSchoolRepositoryImplWithStatusDataTest {

    @Autowired
    private SchoolRepository repository;

    @BeforeAll
    void createDataForTests() {
        repository.saveAll(schoolsToTest());
    }

    @Test
    void shouldSearchRequestOnlyByIsOursStatus() {
        Boolean isOurs = true;
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .isOurs(isOurs)
                .build();
        int expectedNumberOfSchools = 2;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        assertEquals("Washington Middle School", schoolBySearchRequest.get(1).getName());
    }

    @Test
    void shouldSearchRequestOnlyByIsNotOursInSpecificVoivodeship() {
        Boolean isOurs = false;
        String schoolVoivodeshipToSearch = "Warmińsko-Mazurskie";
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .voivodeship(schoolVoivodeshipToSearch)
                .isOurs(isOurs)
                .build();
        int expectedNumberOfSchools = 1;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Another High School", schoolBySearchRequest.get(0).getName());
    }

    @Test
    void shouldSearchRequestOnlyByIsContractedStatus() {
        Boolean isContracted = true;
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .isContracted(isContracted)
                .build();
        int expectedNumberOfSchools = 3;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        assertEquals("Washington Middle School", schoolBySearchRequest.get(1).getName());
        assertEquals("Another High School", schoolBySearchRequest.get(2).getName());
    }

    @Test
    void shouldSearchRequestOnlyByIsNotPhotoStatus() {
        Boolean isPhoto = false;
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .isPhoto(isPhoto)
                .build();
        int expectedNumberOfSchools = 2;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Central High School", schoolBySearchRequest.get(0).getName());
        assertEquals("Washington Middle School", schoolBySearchRequest.get(1).getName());
    }

    @Test
    void shouldSearchRequestOnlyByIncorrectIsPhotoStatus() {
        Boolean isPhoto = null;
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .isPhoto(isPhoto)
                .build();
        int expectedNumberOfSchools = 0;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
    }

    @Test
    void shouldSearchRequestOnlyByIsSettleStatus() {
        Boolean isSettle = true;
        SchoolSearchRequest request = SchoolSearchRequest.builder()
                .isSettle(isSettle)
                .build();
        int expectedNumberOfSchools = 1;

        List<ShortSchoolDTO> schoolBySearchRequest = repository.findSchoolBySearchRequest(request);

        assertEquals(expectedNumberOfSchools, schoolBySearchRequest.size());
        assertEquals("Washington Middle School", schoolBySearchRequest.get(0).getName());
    }

    private List<School> schoolsToTest() {
        List<School> schools = new ArrayList<>();
        School school1 = new School(1L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        School school2 = new School(2L, "Middle School", "Washington Middle School", "info@washingtonmiddle.edu", "www.washingtonmiddle.edu", "Public",
                new Address(Voivodeship.LUBELSKIE, "Lublin County", "Lublin", "Lublin", "Lipowa 5", "20-002"));

        School school3 = new School(3L, "High School", "Another High School", "info@anothercentralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        AdditionalSchoolInformation addInfo1 = new AdditionalSchoolInformation(
                new Status(true, true, false, false, "notka statusu"), null, null);
        AdditionalSchoolInformation addInfo2 = new AdditionalSchoolInformation(
                new Status(true, true, false, true, "notka statusu"), null, null);
        AdditionalSchoolInformation addInfo3 = new AdditionalSchoolInformation(
                new Status(false, true, true, false, "notka statusu"), null, null);
        school1.setAdditionalSchoolInformation(addInfo1);
        school2.setAdditionalSchoolInformation(addInfo2);
        school3.setAdditionalSchoolInformation(addInfo3);

        schools.add(school1);
        schools.add(school2);
        schools.add(school3);

        return schools;
    }
}
