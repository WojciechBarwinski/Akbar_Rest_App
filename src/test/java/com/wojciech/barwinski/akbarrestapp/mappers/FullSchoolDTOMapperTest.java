package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.PhoneDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullSchoolDTOMapperTest {

    private final FullSchoolDTOMapper mapper = new FullSchoolDTOMapper(new ModelMapper());

    @Test
    void shouldMapSchoolToFullSchoolDTO() {
        School schoolToMapping = getSchoolToMapping();
        FullSchoolDTO expectedMapResult = getFullSchoolDTO();

        FullSchoolDTO mapResult = mapper.mapSchoolToFullSchoolDTO(schoolToMapping);

        assertEquals(expectedMapResult, mapResult);
    }


    private School getSchoolToMapping() {

        School school = new School(1234L, "High School", "Central High School", "info@centralhigh.edu", "www.centralhigh.edu", "Public",
                new Address(Voivodeship.WARMINSKO_MAZURSKIE, "Olsztyn County", "Olsztyn", "Olsztyn", "Słoneczna 15", "10-123"));

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

        school.setAdditionalSchoolInformation(addInfo);

        return school;
    }

    private FullSchoolDTO getFullSchoolDTO(){
        FullSchoolDTO schoolDTO = new FullSchoolDTO();
        schoolDTO.setRspo(1234L);
        schoolDTO.setName("Central High School");
        schoolDTO.setVoivodeship(Voivodeship.WARMINSKO_MAZURSKIE);
        schoolDTO.setCounty("Olsztyn County");
        schoolDTO.setBorough("Olsztyn");
        schoolDTO.setCity("Olsztyn");
        schoolDTO.setStreet("Słoneczna 15");

        PhoneDTO workPhone = new PhoneDTO("Work phone", "John Doe", "Work phone");
        PhoneDTO personalPhone = new PhoneDTO("Personal phone", "Jane Doe", "Personal phone");

        schoolDTO.setPhones(List.of(workPhone, personalPhone));

        AdditionalSchoolInformationDTO additionalInfoDTO = new AdditionalSchoolInformationDTO();
        additionalInfoDTO.setStatusNote("notka statusu");
        additionalInfoDTO.setContact(LocalDate.of(2022, 10, 15));
        additionalInfoDTO.setSignContractDate(LocalDate.of(2022, 10, 20));
        additionalInfoDTO.setPhotographingDate(LocalDate.of(2022, 11, 1));
        additionalInfoDTO.setPhotographyDaysCount(3);
        additionalInfoDTO.setPayoff(LocalDate.of(2022, 11, 15));
        additionalInfoDTO.setOther(LocalDate.of(2022, 11, 10));
        additionalInfoDTO.setScheduleNote("Schedule for client John Doe");
        additionalInfoDTO.setNotation1("note1");
        additionalInfoDTO.setNotation2("note2");
        additionalInfoDTO.setNotation3("note3");
        additionalInfoDTO.setPhoto(false);
        additionalInfoDTO.setContracted(true);
        additionalInfoDTO.setSettle(false);
        additionalInfoDTO.setOurs(true);

        schoolDTO.setAdditionalSchoolInformationDTO(additionalInfoDTO);

        return schoolDTO;
    }

}