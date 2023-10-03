package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;

import java.util.List;

public interface SchoolService {
    List<ShortSchoolDTO> getAllSchools();

    SchoolDTO getSchoolById(Long id);
}
