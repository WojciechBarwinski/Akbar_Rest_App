package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import com.wojciech.barwinski.akbarrestapp.mappers.repositories.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Supplier;


@Slf4j
@Service
public class SchoolUpdaterService {

    private final SchoolRepository schoolRepository;

    public SchoolUpdaterService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    FullSchoolDTO updateSchool(FullSchoolDTO schoolToUpdate) {

        log.debug("Start update school");
        School school = schoolRepository.findById(schoolToUpdate.getRspo())
                .orElseThrow(() -> new EntityNotFoundException("School not found with rspo: " + schoolToUpdate.getRspo()));

        overrideValue(schoolToUpdate::getName, school::setName);
        overrideValue(schoolToUpdate::getType, school::setType);
        overrideValue(schoolToUpdate::getEmail, school::setEmail);
        overrideValue(schoolToUpdate::getWebsite, school::setWebsite);
        overrideValue(schoolToUpdate::getStatus, school::setStatus);

        overrideAddress(schoolToUpdate, school);
        overrideAdditionalSchoolInformation(schoolToUpdate, school);

        School school1 = school;

        return null;
    }

    private void overrideAddress(FullSchoolDTO schoolToUpdate, School school) {
        Address address = school.getAddress();

        overrideValue(schoolToUpdate::getVoivodeship, address::setVoivodeship);
        overrideValue(schoolToUpdate::getCounty, address::setCounty);
        overrideValue(schoolToUpdate::getBorough, address::setBorough);
        overrideValue(schoolToUpdate::getCity, address::setCity);
        overrideValue(schoolToUpdate::getStreet, address::setStreet);
        overrideValue(schoolToUpdate::getZipCode, address::setZipCode);
        overrideValue(schoolToUpdate::getAddressNote, address::setAddressNote);
    }

    private void overrideAdditionalSchoolInformation(FullSchoolDTO schoolToUpdate, School school) {
        AdditionalSchoolInformationDTO schoolInfoDto = schoolToUpdate.getAdditionalSchoolInformationDTO();
        Status status = school.getAdditionalSchoolInformation().getStatus();
        Notation notation = school.getAdditionalSchoolInformation().getNotation();
        Schedule schedule = school.getAdditionalSchoolInformation().getSchedule();

        overrideValue(schoolInfoDto::getIsOurs, status::setOurs);
        overrideValue(schoolInfoDto::getIsContracted, status::setContracted);
        overrideValue(schoolInfoDto::getIsPhoto, status::setPhoto);
        overrideValue(schoolInfoDto::getIsSettle, status::setSettle);
        overrideValue(schoolInfoDto::getStatusNote, status::setStatusNote);

        overrideValue(schoolInfoDto::getContact, schedule::setContact);
        overrideValue(schoolInfoDto::getSignContractDate, schedule::setSignContractDate);
        overrideValue(schoolInfoDto::getPhotographingDate, schedule::setPhotographingDate);
        overrideValue(schoolInfoDto::getPhotographyDaysCount, schedule::setPhotographyDaysCount);
        overrideValue(schoolInfoDto::getPayoff, schedule::setPayoff);
        overrideValue(schoolInfoDto::getOther, schedule::setOther);
        overrideValue(schoolInfoDto::getScheduleNote, schedule::setScheduleNote);

        overrideValue(schoolInfoDto::getNotation1, notation::setNotation1);
        overrideValue(schoolInfoDto::getNotation2, notation::setNotation2);
        overrideValue(schoolInfoDto::getNotation3, notation::setNotation3);
    }

    private <T> void overrideValue(Supplier<T> getter, Consumer<T> setter) {
        T getterValue = getter.get();
        if (getterValue != null) {
            setter.accept(getterValue);
        }
    }
}
