package com.wojciech.barwinski.akbarrestapp.Controllers;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.UploadSchoolResult;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.services.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Akbar API")
@RestController()
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService SCHOOL_SERVICE) {
        this.schoolService = SCHOOL_SERVICE;
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
    public ResponseEntity<UploadSchoolResult> uploadStudents(@RequestPart("file") MultipartFile file){
        csvChecks(file.getOriginalFilename());

        return ResponseEntity.ok(schoolService.uploadSchool(file));
    }


    private void csvChecks(String fileName){
        if (fileName == null){
            throw new IllegalArgumentException("No file was send");
        }
        if (!fileName.endsWith(".csv")){
            throw new IllegalArgumentException("Only .csv files are allowed!");
        }
    }

}
