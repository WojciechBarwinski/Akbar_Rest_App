package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SchoolService {
    List<SchoolDTOPreview> getAllSchools();

    SchoolDTO getSchoolById(Long id);

    Integer uploadSchool(MultipartFile file);
}
