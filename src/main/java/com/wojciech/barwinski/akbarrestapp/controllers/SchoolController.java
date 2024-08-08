package com.wojciech.barwinski.akbarrestapp.controllers;

import com.wojciech.barwinski.akbarrestapp.customReader.CsvCustomReader;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.JsonSchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.exception.IdMismatchException;
import com.wojciech.barwinski.akbarrestapp.exception.WrongFileTypeException;
import com.wojciech.barwinski.akbarrestapp.services.SchoolServiceFacade;
import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.UpdateSchoolResultDTO;
import com.wojciech.barwinski.akbarrestapp.validator.toUpload.UploadSchoolResultDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Tag(name = "Akbar API - schools")
@RestController()
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolServiceFacade schoolServiceFacade;
    private final CsvCustomReader csvCustomReader;

    @GetMapping(value = "/all")
    public List<SchoolToRosterDTO> readAllSchools(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        return schoolServiceFacade.getAllSchools(page, size);
    }

    @GetMapping(value = "/{id}")
    public SchoolToViewDTO readSchoolById(@PathVariable Long id) {
        return schoolServiceFacade.getSchoolById(id);
    }

    @PostMapping(consumes = {"application/json"})
    public List<SchoolToRosterDTO> getSchoolsBySearchRequest(@RequestBody SchoolSearchRequest searchRequest){
        return schoolServiceFacade.getSchoolsBySearchRequest(searchRequest);
    }

    @PostMapping(value = "/uploadByCsv", consumes = {"multipart/form-data"})
    public UploadSchoolResultDTO uploadNewSchoolsByCsv(@RequestPart("file") MultipartFile file) {

        checkIfFileIsCsv(file);
        List<SchoolRepresentation> schoolRepresentations = csvCustomReader.getSchoolCsvRepresentationsFromFile(file);

        return schoolServiceFacade.uploadSchools(schoolRepresentations);
    }

    @PostMapping(value = "/uploadByJson", consumes = {"application/json"})
    public UploadSchoolResultDTO uploadNewSchoolsByJson(@RequestBody List<JsonSchoolRepresentation> schoolsJsonRepresentations) {

        return schoolServiceFacade.uploadSchools(schoolsJsonRepresentations);
    }

    @GetMapping(value = "update/{id}")
    public SchoolToUpdateDTO getSchoolToUpdateById(@PathVariable Long id) {
        return schoolServiceFacade.getSchoolToUpdateDTO(id);
    }

    @PutMapping("update/{id}")
    public UpdateSchoolResultDTO updateSchool(@PathVariable Long id, @RequestBody SchoolToUpdateDTO school){
        checkIfIdAndRSPOAreTheSame(id, school.getRspo());

        return schoolServiceFacade.updateSchool(school);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSchool(@PathVariable Long id){
        schoolServiceFacade.deleteSchool(id);
    }


    private void checkIfFileIsCsv(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if ((originalFilename == null) || (!originalFilename.toLowerCase().endsWith(".csv"))) {
            WrongFileTypeException fileIsNotCsvFile = new WrongFileTypeException("Przesłany plik nie jest plikiem .csv");
            log.warn(fileIsNotCsvFile.getMessage(), fileIsNotCsvFile);
            throw fileIsNotCsvFile;
        }
    }

    private void checkIfIdAndRSPOAreTheSame(Long id, Long rspo){
        if (!id.equals(rspo)){
            IdMismatchException exception = new IdMismatchException("id i rspo są różne!");
            log.warn(exception.getMessage(), exception);
            throw exception;
        }
    }
}
