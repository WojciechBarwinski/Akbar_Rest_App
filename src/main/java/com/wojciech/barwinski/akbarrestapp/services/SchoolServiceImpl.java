package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.CsvReader;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapper;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final CsvReader csvReader;

    public SchoolServiceImpl(SchoolRepository SCHOOL_REPOSITORY, SchoolMapper schoolMapper, CsvReader csvReader) {
        this.schoolRepository = SCHOOL_REPOSITORY;
        this.schoolMapper = schoolMapper;
        this.csvReader = csvReader;
    }

    @Override
    public List<SchoolDTOPreview> getAllSchools() {
        List<School> allSchools = schoolRepository.findAll();
        return allSchools.stream()
                .map(schoolMapper::mapSchoolToSchoolDTOPreview)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDTO getSchoolById(Long id) {
        School byRspo = schoolRepository.findByRspo(id).get();
        //TODO exception and mapper to SchoolDTO
        return schoolMapper.mapSchoolToFullSchoolDTO(byRspo);
    }

    @Override
    public Integer uploadSchool(MultipartFile file) {
        Set<School> schools = csvReader.parseCsvByMultipartFile(file);
        for (School school : schools) {
            System.out.println(school.getName());
        }
        return schools.size();
    }
}
