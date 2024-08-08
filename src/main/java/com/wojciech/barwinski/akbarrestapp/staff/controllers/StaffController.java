package com.wojciech.barwinski.akbarrestapp.staff.controllers;

import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.staff.services.StaffServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Akbar API - staff")
@RestController()
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffServiceFacade staffService;

    @GetMapping(value = "/salesman")
    public SalesmanDTO getSalesman(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String lastname) {
        if (id != null) {
            return staffService.getSalesmanById(id);
        } else if (lastname != null) {
            return staffService.getSalesmanByLastname(lastname);
        } else {
            throw new IllegalArgumentException("Either id or lastname must be provided");
        }
    }

    @GetMapping(value = "/photographer")
    public PhotographerDTO getPhotographer(@RequestParam(required = false) Long id,
                                           @RequestParam(required = false) String lastname) {
        if (id != null) {
            return staffService.getPhotographerById(id);
        } else if (lastname != null) {
            return staffService.getPhotographerByLastname(lastname);
        } else {
            throw new IllegalArgumentException("Either id or lastname must be provided");
        }
    }


    @PostMapping(value = "/salesman", consumes = {"application/json"})
    public SalesmanDTO createSalesman(@Valid @RequestBody CreateStaffDTO createStaffDTO){
        return staffService.createSalesman(createStaffDTO);
    }

    @PostMapping(value = "/photographer", consumes = {"application/json"})
    public PhotographerDTO createPhotographer(@Valid @RequestBody CreateStaffDTO createStaffDTO){
        return staffService.createPhotographer(createStaffDTO);
    }
}

