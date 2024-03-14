package com.wojciech.barwinski.akbarrestapp.Controllers;

import com.wojciech.barwinski.akbarrestapp.customReader.CsvCustomReader;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.JsonSchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.exception.IdMismatchException;
import com.wojciech.barwinski.akbarrestapp.exception.WrongFileTypeException;
import com.wojciech.barwinski.akbarrestapp.services.SchoolServiceFacade;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.UploadSchoolResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Tag(name = "Akbar API")
@RestController()
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolServiceFacade schoolServiceFacade;
    private final CsvCustomReader csvCustomReader;

    public SchoolController(SchoolServiceFacade schoolServiceFacade, CsvCustomReader csvCustomReader) {
        this.schoolServiceFacade = schoolServiceFacade;
        this.csvCustomReader = csvCustomReader;
    }

    @GetMapping(value = "/all")
    public List<ShortSchoolDTO> readAllSchools() {
        return schoolServiceFacade.getAllSchools();
    }

    @GetMapping(value = "/{id}")
    public FullSchoolDTO readSchoolById(@PathVariable Long id) {
        return schoolServiceFacade.getSchoolById(id);
    }

    @PostMapping(consumes = {"application/json"})
    public List<ShortSchoolDTO> getSchoolsBySearchRequest(@RequestBody SchoolSearchRequest searchRequest){
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

    @PutMapping("/{id}")
    public FullSchoolDTO updateSchool(@PathVariable Long id, @RequestBody FullSchoolDTO school){
        checkIfIdAndRSPOAreTheSame(id, school.getRspo());

        return schoolServiceFacade.updateSchool(school);
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
