package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class ShortSchoolDTOMapper {

    private final ModelMapper modelMapper;

    public ShortSchoolDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    ShortSchoolDTO mapSchoolToShortSchoolDTO(School school) {
        log.debug("Mapping school to ShortSchoolDTO " + school.getName());

        ShortSchoolDTO dto = modelMapper.map(school, ShortSchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        if (!school.getPhones().isEmpty()) {
            modelMapper.map(school.getPhones().get(0), dto);
        }

        log.debug("Mapping completed");
        return dto;
    }
}
