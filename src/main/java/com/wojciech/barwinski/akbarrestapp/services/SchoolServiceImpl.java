package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.validator.SchoolRepresentationValidator;
import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.UploadSchoolResultDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromSchoolImportDTO;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final MapperFacade mapperFacade;
    private final SchoolRepresentationValidator schoolRepresentationValidator;


    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolRepresentationValidator schoolRepresentationValidator) {
        this.schoolRepository = schoolRepository;
        this.schoolRepresentationValidator = schoolRepresentationValidator;
        this.mapperFacade = MapperFacade.getInstance();
    }

    @Override
    public List<ShortSchoolDTO> getAllSchools() {

        List<School> allSchools = schoolRepository.findAll();
        return allSchools.stream()
                .map(mapperFacade::mapSchoolToShortSchoolDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FullSchoolDTO getSchoolById(Long id) {
        log.info("get school by id " + id);
        Optional<School> optionalSchool = schoolRepository.findByRspo(id);
        if (optionalSchool.isPresent()) {
            School byRspo = optionalSchool.get();
            return mapperFacade.mapSchoolToFullSchoolDTO(byRspo);
        } else {
            NoSuchElementException exception = new NoSuchElementException("Nie można znaleźć szkoły o ID: " + id);
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public UploadSchoolResultDTO uploadSchool(List<SchoolRepresentation> schoolsToImport) {

        List<ValidationReportFromSchoolImportDTO> validateReports = schoolRepresentationValidator.schoolsValidate(schoolsToImport);
        List<SchoolRepresentation> correctSchoolsRepresentation = getCorrectSchoolsRepresentations(schoolsToImport, validateReports);

        List<School> schoolsToSave = correctSchoolsRepresentation.stream()
                .map(mapperFacade::mapSchoolCsvRepresentationToSchool)
                .toList();

        List<ShortSchoolDTO> shortSchoolDTOS = schoolRepository.saveAll(schoolsToSave).stream()
                .map(mapperFacade::mapSchoolToShortSchoolDTO)
                .toList();

        return new UploadSchoolResultDTO(validateReports, shortSchoolDTOS);
    }

    private List<SchoolRepresentation> getCorrectSchoolsRepresentations(List<SchoolRepresentation> schoolsToImport, List<ValidationReportFromSchoolImportDTO> validateReports) {

        List<SchoolRepresentation> listOfCorrectSchoolsRepresentation = new ArrayList<>();
        for (int i = 0; i < validateReports.size(); i++) {
            ValidationStatus status = validateReports.get(i).getStatus();
            if (status != ValidationStatus.ERROR) {
                listOfCorrectSchoolsRepresentation.add(
                        schoolsToImport.get(i)
                );
            }
        }
        return listOfCorrectSchoolsRepresentation;
    }
}
