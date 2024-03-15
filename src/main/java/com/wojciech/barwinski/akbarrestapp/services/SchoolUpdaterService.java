package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.*;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.validator.SchoolUpdateValidator;
import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromUpdateSchool;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;


@Slf4j
@Service
public class SchoolUpdaterService {

    private final SchoolRepository schoolRepository;
    private final SchoolUpdateValidator validator;
    private final MapperFacade mapperFacade;

    public SchoolUpdaterService(SchoolRepository schoolRepository, SchoolUpdateValidator validator) {
        this.schoolRepository = schoolRepository;
        this.validator = validator;
        this.mapperFacade = MapperFacade.getInstance();
    }


    @Transactional
    SchoolToViewDTO updateSchool(SchoolToUpdateDTO schoolToUpdate) {

        log.debug("Start update school");
        School school = schoolRepository.findById(schoolToUpdate.getRspo())
                .orElseThrow(() -> new EntityNotFoundException("School not found with rspo: " + schoolToUpdate.getRspo()));

        checkIfSchoolPassValidation(schoolToUpdate);

        overrideValue(schoolToUpdate::getName, school::setName);
        overrideValue(schoolToUpdate::getType, school::setType);
        overrideValue(schoolToUpdate::getEmail, school::setEmail);
        overrideValue(schoolToUpdate::getWebsite, school::setWebsite);
        overrideValue(schoolToUpdate::getStatus, school::setStatus);

        overrideAddress(schoolToUpdate, school);
        overrideAdditionalSchoolInformation(schoolToUpdate.getAdditionalSchoolInformationDTO(), school);
        overridePhones(schoolToUpdate.getPhones(), school);

        return mapperFacade.mapSchoolToSchoolToViewDTO(schoolRepository.save(school));
    }

    private void checkIfSchoolPassValidation(SchoolToUpdateDTO schoolToUpdate) {
        ValidationReportFromUpdateSchool reportFromValidation = validator.validateSchool(schoolToUpdate);

        if (reportFromValidation.getStatus() == ValidationStatus.ERROR) {
            //TODO Throw exception
        }
    }

    private void overrideAddress(SchoolToUpdateDTO schoolToUpdate, School school) {
        Address address = school.getAddress();

        overrideValue(schoolToUpdate::getVoivodeship, address::setVoivodeship);
        overrideValue(schoolToUpdate::getCounty, address::setCounty);
        overrideValue(schoolToUpdate::getBorough, address::setBorough);
        overrideValue(schoolToUpdate::getCity, address::setCity);
        overrideValue(schoolToUpdate::getStreet, address::setStreet);
        overrideValue(schoolToUpdate::getZipCode, address::setZipCode);
        overrideValue(schoolToUpdate::getAddressNote, address::setAddressNote);
    }

    private void overrideAdditionalSchoolInformation(AdditionalSchoolInformationDTO schoolInfoDto, School school) {
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

    private void overridePhones(List<PhoneToUpdateDTO> phones, School school) {
        List<Phone> phonesFromDB = school.getPhones();
        for (PhoneToUpdateDTO phoneToUpdate : phones) {
            if (phoneToUpdate.isToRemove()) {
                phonesFromDB.removeIf(phoneDB -> Objects.equals(phoneDB.getId(), phoneToUpdate.getId()));
            }

            Phone phoneFromDB = phonesFromDB.stream().filter(x -> Objects.equals(x.getId(), phoneToUpdate.getId()))
                    .toList().get(0);
            overrideValue(phoneToUpdate::getNumber, phoneFromDB::setNumber);
            overrideValue(phoneToUpdate::isMain, phoneFromDB::setMain);
            overrideValue(phoneToUpdate::getOwner, phoneFromDB::setOwner);
            overrideValue(phoneToUpdate::getPhoneNote, phoneFromDB::setPhoneNote);
        }

    }

    private <T> void overrideValue(Supplier<T> getter, Consumer<T> setter) {
        T getterValue = getter.get();
        if (getterValue != null) {
            setter.accept(getterValue);
        }
    }

}
