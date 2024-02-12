package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.csv.Validators.pojo.UploadSchoolResult;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SchoolService {
    List<ShortSchoolDTO> getAllSchools();

    FullSchoolDTO getSchoolById(Long id);

    UploadSchoolResult uploadSchool(MultipartFile file);
}
