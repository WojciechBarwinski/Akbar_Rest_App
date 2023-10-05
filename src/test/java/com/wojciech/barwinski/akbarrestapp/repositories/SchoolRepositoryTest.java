package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest()
class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    Environment env;

    @Test
    void shouldCheckIfTestProfileIsActive() {
        log.info("check profile test start");
        String checkProfile = Arrays.stream(env.getActiveProfiles()).toList().get(0);
        assertThat(checkProfile)
                .isEqualTo("test");
    }

    @Test
    @Transactional
    void testSchoolsSaved() {
        log.info("check if schools are save test start");
        List<School> schools = schoolRepository.findAll();
        assertThat(schools)
                .hasSize(2)
                .isNotEmpty();
    }

    @Test
    @Transactional
    void testSchoolToPhoneCascadePersist() {
        log.info("check that School -> Phone cascade persist work test start");
        log.info("finding school by id");
        School school = schoolRepository.findByRspo(11111L)
                .orElseThrow();
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
        log.info("saving school with phones");
        schoolRepository.saveAndFlush(school);

        log.info("finding phones");
        assertThat(phoneRepository.findAll())
                .hasSize(2)
                .extracting(Phone::getId)
                .doesNotContainNull();
    }


}