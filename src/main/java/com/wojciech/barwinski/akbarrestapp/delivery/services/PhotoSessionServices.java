package com.wojciech.barwinski.akbarrestapp.delivery.services;

import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.CreatePhotoSessionDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.PhotoSessionsByPhotographDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.PhotoSessionsBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.UpdatePhotoSessionDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.delivery.exceptions.NoneDeliveryException;
import com.wojciech.barwinski.akbarrestapp.delivery.mappers.DeliveryMapper;
import com.wojciech.barwinski.akbarrestapp.delivery.repositories.PhotoSessionRepository;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.exception.SchoolNotFoundException;
import com.wojciech.barwinski.akbarrestapp.repositories.SchoolRepository;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.exceptions.StaffNotFoundException;
import com.wojciech.barwinski.akbarrestapp.staff.repositories.PhotographerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class PhotoSessionServices {

    private final PhotoSessionRepository photoSessionRepository;
    private final SchoolRepository schoolRepository;
    private final PhotographerRepository photographerRepository;
    private final DeliveryMapper mapper;

    public PhotoSessionsBySchoolDTO createPhotoSession(CreatePhotoSessionDTO createStaffDTO) {
        School school = schoolRepository.findByRspo(createStaffDTO.getSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("Nie można znaleźć szkoły o ID: " + createStaffDTO.getSchoolId()));

        Photographer photographer = photographerRepository.findById(createStaffDTO.getPhotographId())
                .orElseThrow(() -> new StaffNotFoundException(createStaffDTO.getPhotographId().toString()));

        PhotoSession photoSession = PhotoSession.builder()
                .photographer(photographer)
                .school(school)
                .photographingDate(createStaffDTO.getSessionDate())
                .photographyDaysCount(createStaffDTO.getSessionDuration())
                .note(createStaffDTO.getNote())
                .build();

        photoSessionRepository.save(photoSession);
        Set<PhotoSession> sessionsBySchool = photoSessionRepository.findBySchoolRspo(createStaffDTO.getSchoolId());

        return mapper.mapSessionsToSessionsBySchoolDTOS(sessionsBySchool);
    }

    public PhotoSessionsByPhotographDTO getPhotoSessionByPhotographId(Long id) {
        Set<PhotoSession> sessionsByPhotograph = photoSessionRepository.findByPhotographerId(id);

        if (sessionsByPhotograph.isEmpty()) {
            throw new NoneDeliveryException("There is none sessions by photograph with id: " + id);
        }
        return mapper.mapSessionsToSessionsByPhotographDTO(sessionsByPhotograph);
    }

    public PhotoSessionsBySchoolDTO getPhotoSessionBySchoolId(Long id) {
        Set<PhotoSession> sessionsBySchool = photoSessionRepository.findBySchoolRspo(id);

        if (sessionsBySchool.isEmpty()) {
            throw new NoneDeliveryException("There is none sessions by school with id: " + id);
        }

        return mapper.mapSessionsToSessionsBySchoolDTOS(sessionsBySchool);
    }

    public void deletePhotoSessionById(Long id) {
        photoSessionRepository.deleteById(id);
    }

    public PhotoSessionsBySchoolDTO updatePhotoSession(UpdatePhotoSessionDTO updateStaffDTO) {

        School school = schoolRepository.findByRspo(updateStaffDTO.getSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("Nie można znaleźć szkoły o ID: " + updateStaffDTO.getSchoolId()));

        Photographer photographer = photographerRepository.findById(updateStaffDTO.getPhotographId())
                .orElseThrow(() -> new StaffNotFoundException(updateStaffDTO.getPhotographId().toString()));


        PhotoSession photoSession = PhotoSession.builder()
                .id(updateStaffDTO.getId())
                .photographer(photographer)
                .school(school)
                .photographingDate(updateStaffDTO.getSessionDate())
                .photographyDaysCount(updateStaffDTO.getSessionDuration())
                .note(updateStaffDTO.getNote())
                .build();

        photoSessionRepository.save(photoSession);

        Set<PhotoSession> sessionsBySchool = photoSessionRepository.findBySchoolRspo(updateStaffDTO.getSchoolId());

        return mapper.mapSessionsToSessionsBySchoolDTOS(sessionsBySchool);
    }

}
