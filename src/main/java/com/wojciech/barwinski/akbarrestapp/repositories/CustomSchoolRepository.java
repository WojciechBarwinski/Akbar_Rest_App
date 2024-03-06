package com.wojciech.barwinski.akbarrestapp.repositories;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.entities.School;

import java.util.List;

public interface CustomSchoolRepository {

    List<School> findSchoolBySearchRequest(SchoolSearchRequest searchRequest);
}
