package com.wojciech.barwinski.akbarrestapp.Controllers;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.UploadSchoolResult;
import com.wojciech.barwinski.akbarrestapp.csvCustomReder.CsvCustomReader;
import com.wojciech.barwinski.akbarrestapp.csvCustomReder.SchoolCsvRepresentationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.exception.CsvFileReadException;
import com.wojciech.barwinski.akbarrestapp.exception.WrongFileTypeException;
import com.wojciech.barwinski.akbarrestapp.services.SchoolService;
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

    private final SchoolService schoolService;
    private final CsvCustomReader csvCustomReader;

    public SchoolController(SchoolService schoolService, CsvCustomReader csvCustomReader) {
        this.schoolService = schoolService;
        this.csvCustomReader = csvCustomReader;
    }

    @GetMapping()
    public List<ShortSchoolDTO> readAllSchools() {
        return schoolService.getAllSchools();
    }

    @Operation(summary = "Get a school by id/rspo", description = "Returns a school as per the id/rspo")
    @GetMapping(value = "/{id}")
    public FullSchoolDTO readSchoolById(@PathVariable Long id) {
        return schoolService.getSchoolById(id);
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<UploadSchoolResult> uploadStudents(@RequestPart("file") MultipartFile file) {
        checkIfFileIsCsv(file);
        List<SchoolCsvRepresentationDTO> schoolsCsvRepresentations = csvCustomReader.getSchoolCsvRepresentationsFromFile(file);

        return ResponseEntity.ok(schoolService.uploadSchool(schoolsCsvRepresentations));
    }


    private void checkIfFileIsCsv(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if ((originalFilename == null) || (!originalFilename.toLowerCase().endsWith(".csv"))) {
            WrongFileTypeException fileIsNotCsvFile = new WrongFileTypeException("Przesłany plik nie jest plikiem .csv");
            log.warn(fileIsNotCsvFile.getMessage(), fileIsNotCsvFile);
            throw fileIsNotCsvFile;
        }
    }
}
