package com.wojciech.barwinski.akbarrestapp.mappers.repositories;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;

import java.util.List;

public interface CustomSchoolRepository {

    List<ShortSchoolDTO> findSchoolBySearchRequest(SchoolSearchRequest searchRequest);
    //List<School> findSchoolBySearchRequest(SchoolSearchRequest searchRequest);
}
