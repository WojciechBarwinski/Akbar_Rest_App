package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.validator.toUpload.SchoolRepresentationValidator;
import com.wojciech.barwinski.akbarrestapp.validator.toUpload.UploadSchoolResultDTO;
import com.wojciech.barwinski.akbarrestapp.validator.toUpload.ValidationReportFromImportingSchool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
class SchoolUploaderService {

    private final SchoolRepository schoolRepository;
    private final SchoolRepresentationValidator schoolRepresentationValidator;
    private final MapperFacade mapperFacade;
    private final SchoolUploaderHelper schoolUploaderHelper;


    UploadSchoolResultDTO uploadSchools(List<? extends SchoolRepresentation> schoolsToImport) {

        List<SchoolRepresentation> schools = (List<SchoolRepresentation>) schoolsToImport;
        List<ValidationReportFromImportingSchool> validateReports = schoolRepresentationValidator.schoolsValidate(schools);

        List<School> schoolsToSave = schoolUploaderHelper.prepareSchoolToSave(schools, validateReports);

        log.debug("save all correct schools, and map result of save to shortSchoolDTO");


        List<School> schoolList = schoolRepository.saveAll(schoolsToSave);
        List<SchoolToRosterDTO> schoolToRosterDTOS = schoolList.stream().map(mapperFacade::mapSchoolToSchoolToRosterDTO)
                .toList();


        return new UploadSchoolResultDTO(validateReports, schoolToRosterDTOS);
    }
}
