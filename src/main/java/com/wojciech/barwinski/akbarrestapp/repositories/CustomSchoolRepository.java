package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;

import java.util.List;

public interface CustomSchoolRepository {

    List<SchoolToRosterDTO> findSchoolBySearchRequest(SchoolSearchRequest searchRequest);
    //List<School> findSchoolBySearchRequest(SchoolSearchRequest searchRequest);
}
