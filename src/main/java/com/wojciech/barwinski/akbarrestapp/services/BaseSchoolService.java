package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
class BaseSchoolService {

    private final SchoolRepository schoolRepository;
    private final MapperFacade mapperFacade;

    public BaseSchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
        this.mapperFacade = MapperFacade.getInstance();
    }

    List<ShortSchoolDTO> getAllSchools() {
        List<School> allSchools = schoolRepository.findAll();
        log.debug("get all schools. Number of schools:  " + allSchools.size());
        return allSchools.stream()
                .map(mapperFacade::mapSchoolToShortSchoolDTO)
                .collect(Collectors.toList());
    }

    FullSchoolDTO getSchoolById(Long id) {
        log.info("get school by id " + id);
        Optional<School> optionalSchool = schoolRepository.findByRspo(id);
        if (optionalSchool.isPresent()) {
            School byRspo = optionalSchool.get();
            return mapperFacade.mapSchoolToFullSchoolDTO(byRspo);
        } else {
            //TODO wzrobić poprawny wyjątek oraz jego handler
            NoSuchElementException exception = new NoSuchElementException("Nie można znaleźć szkoły o ID: " + id);
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }
}