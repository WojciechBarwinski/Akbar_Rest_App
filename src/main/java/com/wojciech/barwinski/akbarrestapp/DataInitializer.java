package com.wojciech.barwinski.akbarrestapp;

import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.repositories.PhoneRepository;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {


    /*@Autowired
    PhoneRepository phoneRepository;

    @Autowired
    SchoolRepository schoolRepository;*/

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /*System.out.println("start");
        List<Phone> phoneList= new ArrayList<>();
        phoneList.add(new Phone("666-666", "John Doe", "Work phone"));
        phoneList.add(new Phone("777-777", "Jane Doe", "Personal phone"));

        School school = new School(33333L, "z dataInitializer", "Test High School", "test1@example.com", "www.test1.com", "PUBLIC");

        school.setPhones(phoneList);

        schoolRepository.save(school);
        System.out.println("po save");*/

    }
}

