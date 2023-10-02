package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest()
class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolRepositoryTest.class);

    @Autowired
    Environment env;

    private static List<School> schoolList;

    @BeforeAll
    static void setUpBeforeAll() {
        LOGGER.info("setup before all tests start");
        schoolList = Arrays.asList(
                new School(11111L, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"),
                new School(22222L, "Middle School", "Test Middle School", "test2@example.com", "www.test2.com", "PUBLIC"));
    }

    @BeforeEach
    void setUpBeforeEach() {
        LOGGER.info("setup before each test start");
        schoolRepository.saveAllAndFlush(schoolList);
    }

    @Test
    void shouldCheckIfTestProfileIsActive() {
        LOGGER.info("check profile test start");
        String checkProfile = Arrays.stream(env.getActiveProfiles()).toList().get(0);
        assertThat(checkProfile)
                .isEqualTo("test");
    }

    @Test
    @Transactional
    void testSchoolsSaved() {
        LOGGER.info("check if schools are save test start");
        List<School> schools = schoolRepository.findAll();
        assertThat(schools)
                .hasSize(2)
                .isNotEmpty();
    }

    @Test
    @Transactional
    void testSchoolToPhoneCascadePersist() {
        LOGGER.info("check that School -> Phone cascade persist work test start");
        LOGGER.info("finding school by id");
        School school = schoolRepository.findByRspo(11111L)
                .orElseThrow();
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(new Phone.PhoneBuilder()
                .number("555-1234")
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList.add(new Phone.PhoneBuilder()
                .number("555-5678")
                .owner("JJane Doe")
                .phoneNote("Personal phone")
                .build());



        school.setPhones(phoneList);
        LOGGER.info("saving school with phones");
        schoolRepository.saveAndFlush(school);

        LOGGER.info("finding phones");
        assertThat(phoneRepository.findAll())
                .hasSize(2)
                .extracting(Phone::getId)
                .doesNotContainNull();
    }


}