package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
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

@ActiveProfiles("test")
@SpringBootTest()
class SchoolRepositoryTest {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    Environment env;

    private static List<School> schoolList;

    @BeforeAll
    static void setUpBeforeAll() {

        schoolList = Arrays.asList(
                new School(11111L, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"),
                new School(22222L, "Middle School", "Test Middle School", "test2@example.com", "www.test2.com", "PUBLIC"));
    }

    @BeforeEach
    void setUpBeforeEach() {
        schoolRepository.saveAll(schoolList);
    }

    @Test
    void shouldCheckIfTestProfileIsActive(){
        String checkProfile = Arrays.stream(env.getActiveProfiles()).toList().get(0);
        assertThat(checkProfile)
                .isEqualTo("test");
    }

    @Test
    @Transactional
    void testSchoolsSaved() {
        List<School> schools = schoolRepository.findAll();
        assertThat(schools)
                .hasSize(2)
                .isNotEmpty();

    }

    @Test
    @Transactional
    void testSchoolToPhoneCascadePersist() {
        School school = schoolRepository.findByRspo(11111L).get();
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(new Phone("555-1234", "John Doe", "Work phone"));
        phoneList.add(new Phone("555-5678", "Jane Doe", "Personal phone"));

        school.setPhones(phoneList);
        schoolRepository.saveAndFlush(school);

        assertThat(phoneRepository.findAll())
                .hasSize(2)
                .extracting(Phone::getId)
                .doesNotContainNull();

    }


}