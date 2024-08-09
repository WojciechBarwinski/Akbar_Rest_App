package com.wojciech.barwinski.akbarrestapp.staff.services;

import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.UpdateStaffDTO;
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

    public SalesmanDTO getSalesmanByLastname(String lastName) {
        return salesmanService.getSalesmanByLastname(lastName);
    }

    public PhotographerDTO getPhotographerByLastname(String lastName) {
        return photographerService.getPhotographerByLastname(lastName);
    }

    public SalesmanDTO createSalesman(CreateStaffDTO createStaffDTO) {
        return salesmanService.createSalesman(createStaffDTO);
    }

    public PhotographerDTO createPhotographer(CreateStaffDTO createStaffDTO) {
        return photographerService.createPhotographer(createStaffDTO);
    }

    public void deleteSalesmanById(Long id) {
        salesmanService.deleteSalesmanById(id);
    }

    public void deleteSalesmanByLastname(String lastName) {
        salesmanService.deleteSalesmanByLastname(lastName);
    }

    public void deletePhotographerById(Long id) {
        photographerService.deletePhotographerById(id);
    }

    public void deletePhotographerByLastname(String lastName) {
        photographerService.deletePhotographerByLastname(lastName);
    }

    public PhotographerDTO updatePhotographer(UpdateStaffDTO updateStaffDTO) {
        return photographerService.updatePhotographer(updateStaffDTO);
    }

    public SalesmanDTO updateSalesman(UpdateStaffDTO updateStaffDTO) {
        return salesmanService.updateSalesman(updateStaffDTO);
    }
}
