package com.wojciech.barwinski.akbarrestapp.staff.services;

import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceFacade {

    private final SalesmanService salesmanService;
    private final PhotographerService photographerService;

    public SalesmanDTO getSalesmanById(Long id) {
        return salesmanService.getSalesmanById(id);
    }

    public PhotographerDTO getPhotographerById(Long id) {
        return photographerService.getPhotographerById(id);
    }

    public SalesmanDTO getSalesmanByLastname(String lastname) {
        return salesmanService.getSalesmanByLastname(lastname);
    }

    public PhotographerDTO getPhotographerByLastname(String lastname) {
        return photographerService.getPhotographerByLastname(lastname);
    }

    public SalesmanDTO createSalesman(CreateStaffDTO createStaffDTO) {
        return salesmanService.createSalesman(createStaffDTO);
    }

    public PhotographerDTO createPhotographer(CreateStaffDTO createStaffDTO) {
        return photographerService.createPhotographer(createStaffDTO);
    }
}
