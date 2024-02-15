package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
class FullSchoolDTOMapper {

    private final ModelMapper modelMapper;

    public FullSchoolDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    FullSchoolDTO mapSchoolToFullSchoolDTO(School school) {
        log.debug("Mapping school to schoolDTO " + school.getRspo());

        FullSchoolDTO dto = modelMapper.map(school, FullSchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        dto.setAdditionalSchoolInformationDTO(mapInfoToInfoDTO(school.getAdditionalSchoolInformation()));

        log.debug("Mapping completed");
        return dto;
    }

    private AdditionalSchoolInformationDTO mapInfoToInfoDTO(AdditionalSchoolInformation info) {
        log.trace("     Mapping additional info for schoolDTO"); //Taki pomysł. Robiąc takie wcięcia zdecydowanie łatwiej i czytalniej widać to w logach. Może zamiast tego "->" czy coś takiego?

        if (info == null) {
            return new AdditionalSchoolInformationDTO();
        }
        AdditionalSchoolInformationDTO map = modelMapper.map(info, AdditionalSchoolInformationDTO.class);
        modelMapper.map(info.getNotation(), map);
        modelMapper.map(info.getSchedule(), map);
        modelMapper.map(info.getStatus(), map);

        return map;
    }
}
