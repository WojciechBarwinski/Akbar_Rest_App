package com.wojciech.barwinski.akbarrestapp.staff.services;

import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.staff.mappers.StaffMappers;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.PhotographerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PhotographerService {

    private final PhotographerRepository repository;
    private final StaffMappers mappers;

    public PhotographerDTO getPhotographerById(Long id) {
        Photographer photographer = repository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id.toString()));

        return mappers.mapPhotographToDTO(photographer);
    }

    public PhotographerDTO getPhotographerByLastname(String lastname) {
        Photographer photographer = repository.findByLastName(lastname)
                .orElseThrow(() -> new StaffNotFoundException(lastname));

        return mappers.mapPhotographToDTO(photographer);
    }

    public PhotographerDTO createPhotographer(CreateStaffDTO createStaffDTO) {
        Photographer photographer = mappers.mapNewStaffDTOToPhotographer(createStaffDTO);

        Photographer createdPhotograph = repository.save(photographer);

        return mappers.mapPhotographToDTO(createdPhotograph);
    }
}
