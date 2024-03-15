package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class SchoolToViewAndToUpdateMapper {

    private final ModelMapper modelMapper;

    public SchoolToViewAndToUpdateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    SchoolToViewDTO mapSchoolToSchoolToViewDTO(School school) {
        log.debug("Mapping school to schoolDTO " + school.getRspo());

        SchoolToViewDTO dto = modelMapper.map(school, SchoolToViewDTO.class);
        modelMapper.map(school.getAddress(), dto);
        dto.setAdditionalSchoolInformationDTO(mapInfoToInfoDTO(school.getAdditionalSchoolInformation()));

        log.debug("Mapping completed");
        return dto;
    }

    SchoolToUpdateDTO mapSchoolToSchoolToUpdateDTO(School school) {
        log.debug("Mapping school to schoolDTO " + school.getRspo());

        SchoolToUpdateDTO dto = modelMapper.map(school, SchoolToUpdateDTO.class);
        modelMapper.map(school.getAddress(), dto);
        dto.setAdditionalSchoolInformationDTO(mapInfoToInfoDTO(school.getAdditionalSchoolInformation()));

        log.debug("Mapping completed");
        return dto;
    }


    private AdditionalSchoolInformationDTO mapInfoToInfoDTO(AdditionalSchoolInformation info) {
        log.trace("     Mapping additional info for schoolDTO");

        if (info == null) {
            return new AdditionalSchoolInformationDTO();
        }
        AdditionalSchoolInformationDTO map = modelMapper.map(info, AdditionalSchoolInformationDTO.class);
        modelMapper.map(info.getNotation(), map);
        modelMapper.map(info.getSchedule(), map);
        map.setIsOurs(info.getStatus().isOurs());
        map.setIsContracted(info.getStatus().isContracted());
        map.setIsPhoto(info.getStatus().isPhoto());
        map.setIsSettle(info.getStatus().isSettle());

        return map;
    }
}
