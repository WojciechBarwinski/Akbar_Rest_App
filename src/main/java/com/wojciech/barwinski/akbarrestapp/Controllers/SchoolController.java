package com.wojciech.barwinski.akbarrestapp.Controllers;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import com.wojciech.barwinski.akbarrestapp.services.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/{id}")
    public SchoolDTO readSchoolById(@PathVariable Long id){
        return schoolService.getSchoolById(id);
    }
}
