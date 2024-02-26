package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.validator.SchoolRepresentationValidator;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.UploadSchoolResultDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromSchoolImportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
class SchoolUploaderService {

    private final SchoolRepository schoolRepository;
    private final SchoolRepresentationValidator schoolRepresentationValidator;
    private final MapperFacade mapperFacade;
    private final SchoolUploaderHelper schoolUploaderHelper;

    public SchoolUploaderService(SchoolRepository schoolRepository,
                                 SchoolRepresentationValidator schoolRepresentationValidator,
                                 SchoolUploaderHelper schoolUploaderHelper) {
        this.schoolRepository = schoolRepository;
        this.schoolRepresentationValidator = schoolRepresentationValidator;
        this.schoolUploaderHelper = schoolUploaderHelper;
        this.mapperFacade = MapperFacade.getInstance();
    }


    UploadSchoolResultDTO uploadSchools(List<SchoolRepresentation> schoolsToImport) {

        List<ValidationReportFromSchoolImportDTO> validateReports = schoolRepresentationValidator.schoolsValidate(schoolsToImport);

        List<School> schoolsToSave = schoolUploaderHelper.prepareSchoolToSave(schoolsToImport, validateReports);

        log.debug("save all correct schools, and map result of save to shortSchoolDTO");
        /*List<ShortSchoolDTO> shortSchoolDTOS = schoolRepository.saveAll(schoolsToSave)
                .stream()
                .map(mapperFacade::mapSchoolToShortSchoolDTO)
                .toList();*/

        List<School> schoolList = schoolRepository.saveAll(schoolsToSave);
        List<ShortSchoolDTO> shortSchoolDTOS = schoolList.stream().map(mapperFacade::mapSchoolToShortSchoolDTO)
                .toList();


        return new UploadSchoolResultDTO(validateReports, shortSchoolDTOS);
    }
}
