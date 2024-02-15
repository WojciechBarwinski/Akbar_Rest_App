package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.csv.CsvCustomReader;
import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.SchoolRepresentationValidator;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidateReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidationResult;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.UploadSchoolResult;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapper;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    //private final SchoolMapper schoolMapper;
    private final MapperFacade mapperFacade;
    private final CsvCustomReader csvCustomReader;
    private final SchoolRepresentationValidator schoolRepresentationValidator;


    public SchoolServiceImpl(SchoolRepository SCHOOL_REPOSITORY, SchoolMapper schoolMapper, CsvCustomReader csvCustomReader, SchoolRepresentationValidator schoolRepresentationValidator) {
        this.schoolRepository = SCHOOL_REPOSITORY;
        //this.schoolMapper = schoolMapper;
        this.csvCustomReader = csvCustomReader;
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
    public UploadSchoolResult uploadSchool(MultipartFile file) {
        List<SchoolCsvRepresentation> schoolCsvRepresentations = csvCustomReader.parseCsvByMultipartFile(file);

        SchoolRepValidationResult schoolRepValidationResult = schoolRepresentationValidator.schoolsValidate(schoolCsvRepresentations);

        List<SchoolRepValidateReport> validateReport = schoolRepValidationResult.getSchoolValidateReports();
        List<SchoolCsvRepresentation> correctSchoolsRepresentation = schoolRepValidationResult.getSchoolsAfterValidate();

        List<School> schoolsToSave = correctSchoolsRepresentation.stream()
                .map(mapperFacade::mapSchoolCsvRepresentationToSchool).toList();

        List<ShortSchoolDTO> shortSchoolDTOS = schoolRepository.saveAll(schoolsToSave).stream()
                .map(mapperFacade::mapSchoolToShortSchoolDTO)
                .toList();

        return new UploadSchoolResult(validateReport, shortSchoolDTOS);
    }
}
