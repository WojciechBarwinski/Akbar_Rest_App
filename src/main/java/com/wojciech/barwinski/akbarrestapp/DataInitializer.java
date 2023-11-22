package com.wojciech.barwinski.akbarrestapp;

import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final SchoolRepository schoolRepository;
    private final CsvCustomReader csvCustomReader;

    public DataInitializer(SchoolRepository schoolRepository, CsvCustomReader csvCustomReader) {
        this.schoolRepository = schoolRepository;
        this.csvCustomReader = csvCustomReader;
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
        School school1 = new School(1L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        School school2 = new School(2L, "Middle School", "Washington Middle School", "info@washingtonmiddle.edu", "www.washingtonmiddle.edu", "Public",
                new Address(Voivodeship.LUBELSKIE, "Lublin County", "Lublin", "Lublin", "Lipowa 5", "20-002"));

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

        schoolRepository.saveAll(schools);

        for (School school : csvCustomReader.parseCsvByFilePath()) {
            System.out.println(school.getName());
            System.out.println(school.getRspo());
            System.out.println(school.getAddress().getStreet());
            System.out.println(school.getPhones().get(0).getNumber());
        }

    }
}

