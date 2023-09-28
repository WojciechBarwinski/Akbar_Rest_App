package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class SchoolRepositoryTest {

    @Autowired
    SchoolRepository schoolRepository;
    private static List<School> schoolList;

    @BeforeAll
    static void setUp(){
        schoolList = Arrays.asList(
                new School(11111L, "High School", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"),
                new School(22222L, "Middle School", "Test Middle School", "test2@example.com", "www.test2.com", "PUBLIC"));
    }

    @Test
    void shouldFindAllSchools(){

        schoolRepository.saveAll(schoolList);
        List<School> allSchools = schoolRepository.findAll();

        assertThat(allSchools).hasSize(2);
    }



}