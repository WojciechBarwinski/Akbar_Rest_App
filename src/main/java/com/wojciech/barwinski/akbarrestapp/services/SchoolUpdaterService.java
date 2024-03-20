package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.PhoneToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.exception.PhoneException;
import com.wojciech.barwinski.akbarrestapp.exception.SchoolUpdateException;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.PhoneRepository;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.validator.SchoolUpdateValidator;
import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromUpdateSchool;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class SchoolUpdaterService {

    private final SchoolRepository schoolRepository;
    private final SchoolUpdateValidator validator;
    private final MapperFacade mapperFacade;
    private final PhoneRepository phoneRepository;

    public SchoolUpdaterService(SchoolRepository schoolRepository, SchoolUpdateValidator validator, PhoneRepository phoneRepository) {
        this.schoolRepository = schoolRepository;
        this.validator = validator;
        this.phoneRepository = phoneRepository;
        this.mapperFacade = MapperFacade.getInstance();
    }


    @Transactional
    SchoolToViewDTO updateSchool(SchoolToUpdateDTO schoolToUpdate) {
        log.debug("Start update school");
        checkIfSchoolUpdateCanProceed(schoolToUpdate);

        School school = mapperFacade.mapSchoolToUpdateToSchool(schoolToUpdate);
        school.setPhones(updateSchool(schoolToUpdate.getPhones()));


        return mapperFacade.mapSchoolToSchoolToViewDTO(schoolRepository.save(school));
    }

    private void checkIfSchoolUpdateCanProceed(SchoolToUpdateDTO schoolToUpdate) {
        if (!schoolRepository.existsById(schoolToUpdate.getRspo())){
            throw new EntityNotFoundException("School with this rspo: " + schoolToUpdate.getRspo() + " was not found");
        }

        boolean isMoreThenOneMainPhone = schoolToUpdate.getPhones().stream()
                .filter(PhoneToUpdateDTO::isMain)
                .count() != 1;
        if (isMoreThenOneMainPhone){
            throw new PhoneException("There are invalid number od MAIN phone. There can be only one main phone");
        }

        ValidationReportFromUpdateSchool reportFromValidation = validator.validateSchool(schoolToUpdate);

        if (reportFromValidation.getStatus() == ValidationStatus.ERROR) {
            throw new SchoolUpdateException(reportFromValidation, "There are some ERROR with data to update");
        }
    }


    private List<Phone> updateSchool(List<PhoneToUpdateDTO> phones) {
        List<Phone> phonesToSave = new ArrayList<>();

        for (PhoneToUpdateDTO phoneToUpdate : phones) {

            if (phoneToUpdate.isToRemove()) {
                phoneRepository.deleteById(phoneToUpdate.getId());
            } else {
                phonesToSave.add(mapperFacade.mapPhoneToUpdateToPhone(phoneToUpdate));
            }
        }

        return phonesToSave;
    }
}
