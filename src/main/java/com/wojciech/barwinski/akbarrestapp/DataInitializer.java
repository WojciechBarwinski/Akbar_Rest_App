package com.wojciech.barwinski.akbarrestapp;

import com.wojciech.barwinski.akbarrestapp.delivery.PhotoSessionRepository;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.PhotographerRepository;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.SalesmanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final SchoolRepository schoolRepository;
    private final PhotographerRepository photographerRepository;
    private final SalesmanRepository salesmanRepository;
    private final PhotoSessionRepository photoSessionRepository;


    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        log.info("initial data on app start");
        initialDataForDev();
    }

    private void initialDataForDev() {
        List<School> schools = new ArrayList<>();
        School school1 = new School(1L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

        School school2 = new School(2L, "Middle School", "Washington Middle School", "info@washingtonmiddle.edu", "www.washingtonmiddle.edu", "Public",
                new Address(Voivodeship.LUBELSKIE, "Lublin County", "Lublin", "Lublin", "Lipowa 5", "20-002"));

        List<Phone> phoneList1 = new ArrayList<>();
        phoneList1.add(Phone.builder()
                .isMain(true)
                .number("123456789")
                .owner("John Doe")
                .phoneNote("Work phone")
                .build());
        phoneList1.add(Phone.builder()
                .number("987654321")
                .owner("Jane Doe")
                .phoneNote("Personal phone")
                .build());
        school1.setPhones(phoneList1);

        List<Phone> phoneList2 = new ArrayList<>();
        phoneList2.add(Phone.builder()
                .isMain(true)
                .number("321654987")
                .owner("Alice Wu")
                .phoneNote("school phone")
                .build());
        phoneList2.add(Phone.builder()
                .number("789456123")
                .owner("Alice Wu")
                .phoneNote("Personal phone")
                .build());
        school2.setPhones(phoneList2);

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
        initialStaffData();
        initialDeliveryData();
    }

    private void initialStaffData() {
        Salesman salesman = Salesman.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan.kowalski@mail.com")
                .phone("123-456-789")
                .note("notka pana Kowalskiego")
                .build();

        salesmanRepository.save(salesman);

        Photographer photograph = Photographer.builder()
                .firstName("Tomasz")
                .lastName("Bednarz")
                .email("tom.bednarz@mail.com")
                .phone("987-654-321")
                .note("notatka pana Bednarza")
                .build();

        photographerRepository.save(photograph);
    }


    private void initialDeliveryData(){
        School school1 = schoolRepository.findByRspo(1L).get();
        School school2 = schoolRepository.findByRspo(2L).get();
        Photographer photographer = photographerRepository.findById(1L).get();

        PhotoSession photoSession1 = PhotoSession.builder()
                .school(school1)
                .photographingDate(LocalDate.of(2022, 11, 1))
                .photographyDaysCount(3)
                .note("ciezka praca na korytarzu")
                .build();

        PhotoSession photoSession2 = PhotoSession.builder()
                .school(school2)
                .photographingDate(LocalDate.of(2021, 5, 16))
                .photographyDaysCount(2)
                .note("przyjemna praca")
                .build();

        photographer.addSession(photoSession1);
        photographer.addSession(photoSession2);

        photographerRepository.save(photographer);


    }
}

