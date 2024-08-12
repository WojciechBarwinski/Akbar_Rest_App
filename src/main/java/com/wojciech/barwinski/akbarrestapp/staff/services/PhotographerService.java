package com.wojciech.barwinski.akbarrestapp.staff.services;

import com.wojciech.barwinski.akbarrestapp.delivery.repositories.PhotoSessionRepository;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.CreateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.PhotographerDTO;
import com.wojciech.barwinski.akbarrestapp.staff.dtos.UpdateStaffDTO;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.staff.mappers.StaffMapper;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.PhotographerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
class PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final StaffMapper mappers;
    private final PhotoSessionRepository photoSessionRepository;

    public PhotographerDTO getPhotographerById(Long id) {

        Photographer photographer = photographerRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id.toString()));

        return mappers.mapPhotographToDTO(photographer);
    }

    public PhotographerDTO getPhotographerByLastname(String lastName) {
        Photographer photographer = photographerRepository.findByLastName(lastName)
                .orElseThrow(() -> new StaffNotFoundException(lastName));

        return mappers.mapPhotographToDTO(photographer);
    }

    public PhotographerDTO createPhotographer(CreateStaffDTO createStaffDTO) {
        Photographer photographer = mappers.mapNewStaffDTOToPhotographer(createStaffDTO);

        Photographer createdPhotograph = photographerRepository.save(photographer);

        return mappers.mapPhotographToDTO(createdPhotograph);
    }

    public void deletePhotographerById(Long id) {
        Photographer photographer = photographerRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id.toString()));

        removePhotographerFromSessions(photographer);

        photographerRepository.deleteById(id);
    }

    public void deletePhotographerByLastname(String lastName) {
        Photographer photographer = photographerRepository.findByLastName(lastName)
                .orElseThrow(() -> new StaffNotFoundException(lastName));
        removePhotographerFromSessions(photographer);
        photographerRepository.deleteByLastName(lastName);
    }

    public PhotographerDTO updatePhotographer(UpdateStaffDTO updateStaffDTO) {
        Photographer photographer = mappers.mapUpdateStaffDTOToPhotographer(updateStaffDTO);

        Photographer updatedPhotographer = photographerRepository.save(photographer);

        return mappers.mapPhotographToDTO(updatedPhotographer);
    }

    void removePhotographerFromSessions(Photographer photographer) {

        Set<PhotoSession> sessions = photographer.getPhotoSessions();

        for (PhotoSession photoSession : sessions) {
            photoSession.setPhotographer(null);
        }

        photoSessionRepository.saveAll(sessions);
    }
}
