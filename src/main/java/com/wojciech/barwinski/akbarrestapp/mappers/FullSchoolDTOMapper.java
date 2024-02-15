package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;

class FullSchoolDTOMapper {


    private final ModelMapper modelMapper;

    public FullSchoolDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    FullSchoolDTO mapSchoolToFullSchoolDTO(School school){
        FullSchoolDTO dto = modelMapper.map(school, FullSchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        dto.setAdditionalSchoolInformationDTO(mapInfoToInfoDTO(school.getAdditionalSchoolInformation()));
        return dto;
    }

    private AdditionalSchoolInformationDTO mapInfoToInfoDTO(AdditionalSchoolInformation info){
        if (info == null){
            return new AdditionalSchoolInformationDTO();
        }
        AdditionalSchoolInformationDTO map = modelMapper.map(info, AdditionalSchoolInformationDTO.class);
        modelMapper.map(info.getNotation(), map);
        modelMapper.map(info.getSchedule(), map);
        modelMapper.map(info.getStatus(), map);

        return map;
    }
}
