package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;

@Slf4j
public class SchoolToRosterMapper {

    private final ModelMapper modelMapper;

    public SchoolToRosterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    SchoolToRosterDTO mapSchoolToSchoolToRosterDTO(School school) {
        log.debug("Mapping school to ShortSchoolDTO " + school.getName());

        SchoolToRosterDTO dto = modelMapper.map(school, SchoolToRosterDTO.class);
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
