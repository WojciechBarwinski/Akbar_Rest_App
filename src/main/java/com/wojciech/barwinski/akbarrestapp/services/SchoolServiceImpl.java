package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.csv.CsvCustomReader;
import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.SchoolRepresentationValidator;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidateReport;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.SchoolRepValidationResult;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.UploadSchoolResult;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapper;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final CsvCustomReader csvCustomReader;
    private final SchoolRepresentationValidator schoolRepresentationValidator;

    public SchoolServiceImpl(SchoolRepository SCHOOL_REPOSITORY, SchoolMapper schoolMapper, CsvCustomReader csvCustomReader, SchoolRepresentationValidator schoolRepresentationValidator) {
        this.schoolRepository = SCHOOL_REPOSITORY;
        this.schoolMapper = schoolMapper;
        this.csvCustomReader = csvCustomReader;
        this.schoolRepresentationValidator = schoolRepresentationValidator;
    }

    @Override
    public List<SchoolDTOPreview> getAllSchools() {
        List<School> allSchools = schoolRepository.findAll();
        return allSchools.stream()
                .map(schoolMapper::mapSchoolToSchoolDTOPreview)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDTO getSchoolById(Long id) {
        School byRspo = schoolRepository.findByRspo(id).get();
        //TODO exception
        return schoolMapper.mapSchoolToFullSchoolDTO(byRspo);
    }

    @Override
    public UploadSchoolResult uploadSchool(MultipartFile file) {
        List<SchoolCsvRepresentation> schoolCsvRepresentations = csvCustomReader.parseCsvByMultipartFile(file);

        SchoolRepValidationResult schoolRepValidationResult = schoolRepresentationValidator.schoolsValidate(schoolCsvRepresentations);

        List<SchoolRepValidateReport> validateReport = schoolRepValidationResult.getSchoolValidateReports();
        List<SchoolCsvRepresentation> correctSchools = schoolRepValidationResult.getSchoolsAfterValidate();

        List<SchoolDTOPreview> schoolDTOPreviews = schoolRepository.saveAll(schoolMapper.mapSchoolCsvRepToSchool(correctSchools))
                .stream()
                .map(schoolMapper::mapSchoolToSchoolDTOPreview).toList();

        return new UploadSchoolResult(validateReport, schoolDTOPreviews);
    }
}
