package com.wojciech.barwinski.akbarrestapp;

import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final SchoolRepository schoolRepository;

    public DataInitializer(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Environment environment = event.getApplicationContext().getEnvironment();
        if (environment.matchesProfiles("test")) {
            log.info("Test DataInitializer starts");
            initialDataForTests();
        } else {
            log.info("DataInitializer starts");
            initialDataForProd();
        }
    }

    private void initialDataForTests() {
        List<School> schoolList = Arrays.asList(
                new School(11111L, "High School123", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC"),
                new School(22222L, "Middle School", "Test Middle School", "test2@example.com", "www.test2.com", "PUBLIC"));
        schoolRepository.saveAll(schoolList);
    }

    private void initialDataForProd() {
        List<School> schools = new ArrayList<>();
        schools.add(new School(1L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123")));

        schools.add(new School(2L, "Middle School", "Washington Middle School", "info@washingtonmiddle.edu", "www.washingtonmiddle.edu", "Public",
                new Address(Voivodeship.LUBELSKIE, "Lublin County", "Lublin", "Lublin", "Lipowa 5", "20-002")));

        schoolRepository.saveAll(schools);
    }
}

