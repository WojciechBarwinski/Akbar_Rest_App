package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.*;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.deliverable.Photography;
import com.wojciech.barwinski.akbarrestapp.entities.deliverable.Trade;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        dto.setDeliverableDTO(mapDeliverablesToDTOForSchoolToViewDTO(school));

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

    DeliverableDTO mapDeliverablesToDTOForSchoolToViewDTO(School school) {

        List<TradeDTO> tradeDTOS = mapTradesToTradeDTOs(school.getTrades());
        List<PhotographyDTO> photographyDTOS = mapPhotographyToDTO(school.getPhotographs());

        return new DeliverableDTO(photographyDTOS, tradeDTOS);
    }

    private List<PhotographyDTO> mapPhotographyToDTO(Set<Photography> photographs) {
        List<PhotographyDTO> photographyDTOS = new ArrayList<>();

        for (Photography photograph : photographs) {
            photographyDTOS.add(
                    PhotographyDTO.builder()
                            .photographer(photograph.getPhotographer().getFirstName() + " " + photograph.getPhotographer().getLastName())
                            .photographingDate(photograph.getPhotographingDate())
                            .photographyDaysCount(photograph.getPhotographyDaysCount())
                            .photographyNote(photograph.getTradeNote())
                            .build()
            );
        }
        return photographyDTOS;
    }

    private List<TradeDTO> mapTradesToTradeDTOs(Set<Trade> trades) {
        List<TradeDTO> tradeDTOS = new ArrayList<>();
        for (Trade trade : trades) {
            tradeDTOS.add(
                    TradeDTO.builder()
                            .salesman(trade.getSalesman().getFirstName() + " " + trade.getSalesman().getLastName())
                            .signContractDate(trade.getSignContractDate())
                            .tradeNote(trade.getTradeNote())
                            .build()
            );
        }
        return tradeDTOS;
    }
}
