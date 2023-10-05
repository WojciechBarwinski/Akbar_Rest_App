package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapperModelTest {

    @Autowired
    ModelMapper modelMapper;


    public ShortSchoolDTO shortSchoolDTOMapperTest(School school) {
        ShortSchoolDTO dto = modelMapper.map(school, ShortSchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        if (!school.getPhones().isEmpty()) {
            dto.setPhone(formatPhoneNumber(
                    school.getPhones().get(0).getNumber()));
        }
        return dto;
    }

    public String formatPhoneNumber(String phone) {
        return phone.substring(0, 3) + "-" +
                phone.substring(3, 6) + "-" +
                phone.substring(6);
    }
}
