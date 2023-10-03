package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository SCHOOL_REPOSITORY;

    public SchoolServiceImpl(SchoolRepository SCHOOL_REPOSITORY) {
        this.SCHOOL_REPOSITORY = SCHOOL_REPOSITORY;
    }

    @Override
    public List<ShortSchoolDTO> getAllSchools() {
        List<School> allSchools = SCHOOL_REPOSITORY.findAll();
        //TODO mapper to shortDTO
        return null;
    }

    @Override
    public SchoolDTO getSchoolById(Long id) {
        Optional<School> byRspo = SCHOOL_REPOSITORY.findByRspo(id);
        //TODO exception and mapper to SchoolDTO

        return null;
    }
}
