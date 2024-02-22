package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.validator.dtos.UploadSchoolResultDTO;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;

import java.util.List;

public interface SchoolService {
    List<ShortSchoolDTO> getAllSchools();

    FullSchoolDTO getSchoolById(Long id);

    UploadSchoolResultDTO uploadSchool(List<SchoolRepresentation> schoolsFromCsv);
}
