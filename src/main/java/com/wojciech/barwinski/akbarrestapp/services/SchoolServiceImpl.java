package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapperModelTest;
import com.wojciech.barwinski.akbarrestapp.mappers.ShortSchoolDTOMapper;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final ShortSchoolDTOMapper shortSchoolDTOMapper;
    private final SchoolMapperModelTest schoolMapperModelTest;

    public SchoolServiceImpl(SchoolRepository SCHOOL_REPOSITORY, ShortSchoolDTOMapper shortSchoolDTOMapper, SchoolMapperModelTest schoolMapperModelTest) {
        this.schoolRepository = SCHOOL_REPOSITORY;
        this.shortSchoolDTOMapper = shortSchoolDTOMapper;
        this.schoolMapperModelTest = schoolMapperModelTest;
    }

    @Override
    public List<ShortSchoolDTO> getAllSchools() {
        List<School> allSchools = schoolRepository.findAll();
        return allSchools.stream()
                .map(schoolMapperModelTest::shortSchoolDTOMapperTest)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDTO getSchoolById(Long id) {
        Optional<School> byRspo = schoolRepository.findByRspo(id);
        //TODO exception and mapper to SchoolDTO

        return null;
    }
}
