package com.wojciech.barwinski.akbarrestapp.staff.mappers;

import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StaffMappers {

    ModelMapper mapper = new ModelMapper();

    public PhotographerDTO mapPhotographToDTO(Photographer photographer) {
        return mapper.map(photographer, PhotographerDTO.class);
    }

    public SalesmanDTO mapSalesmanToDTO(Salesman salesman){
        return mapper.map(salesman, SalesmanDTO.class);
    }

    public Salesman mapNewStaffDTOToSalesman(CreateStaffDTO dto){
        return mapper.map(dto, Salesman.class);
    }

    public Photographer mapNewStaffDTOToPhotographer(CreateStaffDTO dto){
        return mapper.map(dto, Photographer.class);
    }
}
