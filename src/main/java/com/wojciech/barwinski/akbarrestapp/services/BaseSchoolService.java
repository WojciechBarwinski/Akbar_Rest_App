package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.exception.SchoolNotFoundException;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
class BaseSchoolService {

    private final SchoolRepository schoolRepository;
    private final MapperFacade mapperFacade;


    SchoolToViewDTO getSchoolById(Long id) {
        log.info("get school by id " + id);
        Optional<School> optionalSchool = schoolRepository.findByRspo(id);
        if (optionalSchool.isPresent()) {
            School byRspo = optionalSchool.get();
            return mapperFacade.mapSchoolToSchoolToViewDTO(byRspo);
        } else {
            SchoolNotFoundException exception = new SchoolNotFoundException("Nie można znaleźć szkoły o ID: " + id);
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    SchoolToUpdateDTO getSchoolToUpdateById(Long id) {
        log.info("get school by id " + id);
        Optional<School> optionalSchool = schoolRepository.findByRspo(id);
        if (optionalSchool.isPresent()) {
            School byRspo = optionalSchool.get();
            return mapperFacade.mapSchoolToSchoolToUpdateDTO(byRspo);
        } else {
            SchoolNotFoundException exception = new SchoolNotFoundException("Nie można znaleźć szkoły o ID: " + id);
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    List<SchoolToRosterDTO> getAllSchools(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));

        List<SchoolToRosterDTO> allShortSchool = schoolRepository.findAllShortSchool(pageable);
        log.debug("get all schools. Number of schools:  " + allShortSchool.size());
        return allShortSchool;
    }

    List<SchoolToRosterDTO> getSchoolsBySearchRequest(SchoolSearchRequest request){
        return schoolRepository.findSchoolBySearchRequest(request);
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
