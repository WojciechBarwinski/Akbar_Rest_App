package com.wojciech.barwinski.akbarrestapp.Controllers;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import com.wojciech.barwinski.akbarrestapp.services.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<SchoolDTOPreview> readAllHero(){
        return schoolService.getAllSchools();
    }

    @Operation(summary = "Get a school by id/rspo", description = "Returns a school as per the id/rspo")
    @GetMapping(value = "/{id}")
    public SchoolDTO readSchoolById(@PathVariable Long id){
        return schoolService.getSchoolById(id);
    }
}
