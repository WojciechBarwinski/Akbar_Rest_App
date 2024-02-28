package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;

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

        List<Phone> phones = school.getPhones();

        if (phones != null && !phones.isEmpty()) {
            dto.setPhone(school.getPhones().get(0).getNumber());
        } else {
            dto.setPhone("");
        }

        log.debug("Mapping completed");
        return dto;
    }
}
