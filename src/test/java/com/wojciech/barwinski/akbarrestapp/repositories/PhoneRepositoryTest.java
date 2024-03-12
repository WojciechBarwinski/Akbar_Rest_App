package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest()
class PhoneRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    Environment env;

    @BeforeAll
    void createDataForTests() {
        schoolRepository.saveAll(schoolsToTest());
    }

    @Test
    void shouldCheckIfTestProfileIsActive() {
        log.info("Check profile test start");
        String checkProfile = Arrays.stream(env.getActiveProfiles()).toList().get(0);

        assertEquals(checkProfile, "test");
    }

    @Test
    void shouldGetAllPhonesBySchoolRspo() {
        log.info("save and take school");
        //given
        Long schoolRspo = 11111L;
        School school = schoolRepository.save(new School(schoolRspo, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"));

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(Phone.builder()
                .number("970018363")
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList.add(Phone.builder()
                .number("246667788")
                .owner("JJane Doe")
                .phoneNote("Personal phone")
                .build());

        //when
        school.setPhones(phoneList);
        log.info("saving school with phones");
        schoolRepository.saveAndFlush(school);

        //then
        log.info("find all phones by school rspo");
        List<Phone> phones = phoneRepository.findAllPhoneBySchoolRspo(schoolRspo);

        assertAll("phones",
                () -> assertEquals(2, phones.size()),
                () -> assertFalse(phones.isEmpty())
        );
    }

    private static List<School> schoolsToTest() {
        return Arrays.asList(
                new School(11111L, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"),
                new School(22222L, "Middle School", "Test Middle School", "test2@example.com", "www.test2.com", "PUBLIC"));

    }

}