package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class CsvSchoolMapper {

    private final ModelMapper modelMapper;

    public CsvSchoolMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    School mapSchoolCsvRepresentationToSchool(SchoolCsvRepresentation csvRepresentation) {
        log.debug("Mapping CSV representation to School: " + csvRepresentation.getName());

        School school = modelMapper.map(csvRepresentation, School.class);
        Address address = modelMapper.map(csvRepresentation, Address.class);
        address.setVoivodeship(getVoivodeshipFromString(csvRepresentation.getVoivodeship()));
        school.addPhone(getPhoneFromCsv(csvRepresentation.getPhone()));
        school.setAddress(address);

        log.debug("Mapping completed");
        return school;
    }

    private Phone getPhoneFromCsv(String phone) {
        log.trace("     Mapping phone");

        if (phone == null || phone.isEmpty()) {
            return new Phone.PhoneBuilder()
                    .phoneNote("brak numeru szkoły z bazy danych ministerstwa")
                    .build();
        }
        return new Phone.PhoneBuilder()
                .number(phone)
                .owner("Główny numer szkoły")
                .build();
    }

    private Voivodeship getVoivodeshipFromString(String voivodeship) {
        log.trace("     Mapping voivodeship");

        for (Voivodeship v : Voivodeship.values()) {
            if (v.getName().equalsIgnoreCase(voivodeship)) {
                return v;
            }
        }
        return null;
    }
}
