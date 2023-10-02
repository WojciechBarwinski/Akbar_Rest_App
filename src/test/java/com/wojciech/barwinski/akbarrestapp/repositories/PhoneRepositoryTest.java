package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest()
class PhoneRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolRepositoryTest.class);

    @Autowired
    Environment env;


    @Test
    void shouldCheckIfTestProfileIsActive(){
        LOGGER.info("Check profile test start");
        String checkProfile = Arrays.stream(env.getActiveProfiles()).toList().get(0);
        assertThat(checkProfile)
                .isEqualTo("test");
    }

    @Test
    void shouldGetAllPhonesBySchoolRspo(){
        LOGGER.info("save and take school");
        //given
        Long schoolRspo = 11111L;
        School school = schoolRepository.save(new School(schoolRspo, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"));

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(new Phone.PhoneBuilder()
                .number("970018363")
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList.add(new Phone.PhoneBuilder()
                .number("246667788")
                .owner("JJane Doe")
                .phoneNote("Personal phone")
                .build());

        //when
        school.setPhones(phoneList);
        LOGGER.info("saving school with phones");
        schoolRepository.saveAndFlush(school);

        //then
        LOGGER.info("find all phones by school rspo");
        List<Phone> phones = phoneRepository.findAllPhoneBySchoolRspo(schoolRspo);

        assertThat(phones)
                .hasSize(2)
                .isNotEmpty();
    }


}