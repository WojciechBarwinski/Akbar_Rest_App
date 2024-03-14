package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest()
class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    Environment env;

    @BeforeAll
    void createDataForTests() {
        schoolRepository.saveAll(schoolsToTest());
    }

    @Test
    void shouldCheckIfTestProfileIsActive() {
        log.info("check profile test start");
        String checkProfile = Arrays.stream(env.getActiveProfiles()).toList().get(0);

        assertEquals(checkProfile, "test");
    }

    @Test
    @Transactional
    void testSchoolsSaved() {
        log.info("check if schools are save test start");
        List<School> schools = schoolRepository.findAll();

        assertEquals(schools.size(), 2);
    }

    @Test
    @Transactional
    void testSchoolToPhoneCascadePersist() {
        log.info("check that School -> Phone cascade persist work test start");
        log.info("finding school by id");
        School school = schoolRepository.findByRspo(11111L)
                .orElseThrow();
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(Phone.builder()
                .number("123456789")
                .isMain(true)
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList.add(Phone.builder()
                .number("987654321")
                .owner("Jane Doe")
                .phoneNote("Personal phone")
                .build());


        school.setPhones(phoneList);
        log.info("saving school with phones");
        schoolRepository.saveAndFlush(school);

        log.info("finding phones");
        List<Phone> phones = schoolRepository.findByRspo(11111L).get().getPhones();

        assertAll("phones",
                () -> assertEquals(2, phones.size(), "Expected size is 2"),
                () -> assertNotNull(phones.stream().map(Phone::getId))
        );
    }

    private static List<School> schoolsToTest() {
        return Arrays.asList(
                new School(11111L, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"),
                new School(22222L, "Middle School", "Test Middle School", "test2@example.com", "www.test2.com", "PUBLIC"));

    }

}