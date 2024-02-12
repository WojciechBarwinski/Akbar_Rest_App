package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;

public class MapperShortSchoolDTO {

    private final ModelMapper modelMapper;

    public MapperShortSchoolDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    ShortSchoolDTO mapSchoolToShortSchoolDTO(School school) {
        ShortSchoolDTO dto = modelMapper.map(school, ShortSchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        if (!school.getPhones().isEmpty()) {
            modelMapper.map(school.getPhones().get(0), dto);
        }
        return dto;
    }
}
