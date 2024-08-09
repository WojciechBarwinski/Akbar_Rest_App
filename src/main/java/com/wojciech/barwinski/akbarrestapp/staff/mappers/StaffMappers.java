package com.wojciech.barwinski.akbarrestapp.staff.mappers;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.*;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffMappers {

    ModelMapper mapper = new ModelMapper();

    public PhotographerDTO mapPhotographToDTO(Photographer photographer) {
        PhotographerDTO dto = mapper.map(photographer, PhotographerDTO.class);
        dto.setDelivery(mapPhotographyToDeliveryDTOS(photographer));
        return dto;
    }

    public SalesmanDTO mapSalesmanToDTO(Salesman salesman){
        SalesmanDTO dto = mapper.map(salesman, SalesmanDTO.class);
        dto.setDelivery(mapTradesToDeliveryDTOS(salesman));
        return dto;
    }

    public Salesman mapNewStaffDTOToSalesman(CreateStaffDTO dto){
        return mapper.map(dto, Salesman.class);
    }

    public Photographer mapNewStaffDTOToPhotographer(CreateStaffDTO dto){
        return mapper.map(dto, Photographer.class);
    }

    public Photographer mapUpdateStaffDTOToPhotographer(UpdateStaffDTO dto){
        return mapper.map(dto, Photographer.class);
    }

    public Salesman mapUpdateStaffDTOToSalesman(UpdateStaffDTO dto){
        return mapper.map(dto, Salesman.class);
    }

    private List<SalesmanDeliveryDTO> mapTradesToDeliveryDTOS(Salesman salesman){

        List<SalesmanDeliveryDTO> deliveryDTOS = new ArrayList<>();

        for (Trade trade : salesman.getTrades()) {
            deliveryDTOS.add(
                    SalesmanDeliveryDTO.builder()
                            .school(trade.getSchool().getName())
                            .signContractDate(trade.getSignContractDate())
                            .tradeNote(trade.getNote())
                            .build()
            );
        }
        return deliveryDTOS;
    }

    private List<PhotographerDeliveryDTO> mapPhotographyToDeliveryDTOS(Photographer photographer){
        List<PhotographerDeliveryDTO>  list = new ArrayList<>();

        for (PhotoSession session : photographer.getPhotoSessions()) {
            list.add(
                    PhotographerDeliveryDTO.builder()
                            .school(session.getSchool().getName())
                            .photographingDate(session.getPhotographingDate())
                            .photographyDaysCount(session.getPhotographyDaysCount())
                            .note(session.getNote())
                            .build()
            );
        }

        return list;
    }
}
