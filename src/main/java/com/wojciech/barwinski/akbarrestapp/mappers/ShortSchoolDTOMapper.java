package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ShortSchoolDTOMapper implements Function<School, ShortSchoolDTO> {

    @Override
    public ShortSchoolDTO apply(School school) {
        return new ShortSchoolDTO(
                school.getRspo(),
                school.getName(),
                school.getAddress().getVoivodeship().name(),
                school.getAddress().getCounty(),
                school.getAddress().getBorough(),
                school.getAddress().getCity(),
                school.getAddress().getStreet(),
                school.getPhones().get(0).getNumber()
        );
    }
}
