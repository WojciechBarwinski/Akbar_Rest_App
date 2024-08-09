package com.wojciech.barwinski.akbarrestapp.staff.controllers;

import com.wojciech.barwinski.akbarrestapp.exception.IdMismatchException;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.SalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.UpdateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.services.StaffServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
                                   @RequestParam(required = false) String lastName) {
        if (id != null) {
            return staffService.getSalesmanById(id);
        } else if (lastName != null) {
            return staffService.getSalesmanByLastname(lastName);
        } else {
            throw new IllegalArgumentException("Either id or lastname must be provided");
        }
    }

    @GetMapping(value = "/photographer")
    public PhotographerDTO getPhotographer(@RequestParam(required = false) Long id,
                                           @RequestParam(required = false) String lastName) {
        if (id != null) {
            return staffService.getPhotographerById(id);
        } else if (lastName != null) {
            return staffService.getPhotographerByLastname(lastName);
        } else {
            throw new IllegalArgumentException("Either id or lastname must be provided");
        }
    }

    @PostMapping(value = "/salesman", consumes = {"application/json"})
    public SalesmanDTO createSalesman(@Valid @RequestBody CreateStaffDTO createStaffDTO) {
        return staffService.createSalesman(createStaffDTO);
    }

    @PostMapping(value = "/photographer", consumes = {"application/json"})
    public PhotographerDTO createPhotographer(@Valid @RequestBody CreateStaffDTO createStaffDTO) {
        return staffService.createPhotographer(createStaffDTO);
    }

    @PutMapping(value = "/photographer/{id}")
    public PhotographerDTO updatePhotographer(@PathVariable Long id,
                                              @Valid @RequestBody UpdateStaffDTO updateStaffDTO) {
        checkIfIdFromPathAdnFromDTOAreTheSame(id, updateStaffDTO.getId());

        return staffService.updatePhotographer(updateStaffDTO);
    }

    @PutMapping(value = "/salesman/{id}")
    public SalesmanDTO updateSalesman(@PathVariable Long id,
                                      @Valid @RequestBody UpdateStaffDTO updateStaffDTO) {
        checkIfIdFromPathAdnFromDTOAreTheSame(id, updateStaffDTO.getId());

        return staffService.updateSalesman(updateStaffDTO);
    }

    @DeleteMapping(value = "/salesman")
    public ResponseEntity<Void> deleteSalesman(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String lastName) {
        if (id != null) {
            staffService.deleteSalesmanById(id);
        } else if (lastName != null) {
            staffService.deleteSalesmanByLastname(lastName);
        } else {
            throw new IllegalArgumentException("Either id or lastname must be provided");
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/photographer")
    public ResponseEntity<Void> deletePhotographer(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) String lastName) {
        if (id != null) {
            staffService.deletePhotographerById(id);
        } else if (lastName != null) {
            staffService.deletePhotographerByLastname(lastName);
        } else {
            throw new IllegalArgumentException("Either id or lastname must be provided");
        }
        return ResponseEntity.noContent().build();
    }

    private void checkIfIdFromPathAdnFromDTOAreTheSame(Long idFromPath, Long idFromDTO) {
        if (!idFromPath.equals(idFromDTO)) {
            throw new IdMismatchException("id from path and DTO are different!");
        }
    }
}

