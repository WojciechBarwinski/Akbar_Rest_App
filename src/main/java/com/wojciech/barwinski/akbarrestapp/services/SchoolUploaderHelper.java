package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.exception.SchoolException;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import com.wojciech.barwinski.akbarrestapp.validator.ValidationStatus;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.ValidationReportFromSchoolImportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
class SchoolUploaderHelper {

    private final MapperFacade mapperFacade = MapperFacade.getInstance();


    List<School> prepareSchoolToSave(List<SchoolRepresentation> schoolsToImport,
                                     List<ValidationReportFromSchoolImportDTO> validateReports) {

        checkThatBothListsHaveTheSameSize(schoolsToImport, validateReports);


        List<SchoolRepresentation> correctSchoolsRepresentation = getCorrectSchoolsRepresentations(schoolsToImport, validateReports);
        log.debug("map all correct schools representations to schools");
        return correctSchoolsRepresentation.stream()
                .map(mapperFacade::mapSchoolRepresentationToSchool)
                .toList();
    }

    private List<SchoolRepresentation> getCorrectSchoolsRepresentations(List<SchoolRepresentation> schoolsToImport,
                                                                        List<ValidationReportFromSchoolImportDTO> validateReports) {
        log.debug("create list of correct schools base on validation report");
        List<SchoolRepresentation> listOfCorrectSchoolsRepresentation = new ArrayList<>();

        for (int i = 0; i < validateReports.size(); i++) {

            if (checkIfSchoolCanBeSave(validateReports.get(i), schoolsToImport.get(i))) {
                listOfCorrectSchoolsRepresentation.add(schoolsToImport.get(i));
            }
        }
        return listOfCorrectSchoolsRepresentation;
    }

    private void checkThatBothListsHaveTheSameSize(List<SchoolRepresentation> schoolsToImport,
                                                   List<ValidationReportFromSchoolImportDTO> validateReports) {

        if (schoolsToImport.size() != validateReports.size()) {
            SchoolException exception = new SchoolException("SchoolsToImport and ValidateReport have diffrent size. Something is wrong");
            log.warn(exception.getMessage(), exception);
            throw exception;
        }
    }

    private boolean checkIfSchoolCanBeSave(ValidationReportFromSchoolImportDTO report, SchoolRepresentation school) {
        return (report.getStatus() != ValidationStatus.ERROR) && (report.getRspo().equals(school.getRspo()));
    }
}

