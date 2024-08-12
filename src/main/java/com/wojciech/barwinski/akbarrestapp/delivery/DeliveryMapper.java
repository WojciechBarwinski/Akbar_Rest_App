package com.wojciech.barwinski.akbarrestapp.delivery;

import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.PhotoSessionsByPhotographDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.PhotoSessionsBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.SessionByPhotographDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionsDTOS.SessionBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DeliveryMapper {

    public PhotoSessionsBySchoolDTO mapSessionsToSessionsBySchoolDTOS(Set<PhotoSession> sessionsBySchool) {
        School school = sessionsBySchool.stream().findFirst().get().getSchool();
        List<SessionBySchoolDTO> photoSessionDTOS = new ArrayList<>();

        for (PhotoSession photoSession : sessionsBySchool) {
            photoSessionDTOS.add(
                    SessionBySchoolDTO.builder()
                    .photographer(photoSession.getPhotographer().getFirstName() + " " + photoSession.getPhotographer().getLastName())
                    .photographingDate(photoSession.getPhotographingDate())
                    .photographyDaysCount(photoSession.getPhotographyDaysCount())
                    .photographyNote(photoSession.getNote())
                    .build());
        }
        return new PhotoSessionsBySchoolDTO(school.getRspo(),
                school.getName(),
                photoSessionDTOS);
    }

    public PhotoSessionsByPhotographDTO mapSessionsToSessionsByPhotographDTO(Set<PhotoSession> sessionsByPhotograph) {
        Photographer photographer = sessionsByPhotograph.stream().findFirst().get().getPhotographer();
        List<SessionByPhotographDTO> photoSessionDTOS = new ArrayList<>();

        for (PhotoSession photoSession : sessionsByPhotograph) {
            photoSessionDTOS.add(
                    SessionByPhotographDTO.builder()
                    .schoolName(photoSession.getSchool().getName())
                    .photographingDate(photoSession.getPhotographingDate())
                    .photographyDaysCount(photoSession.getPhotographyDaysCount())
                    .photographyNote(photoSession.getNote())
                    .build());
        }
        return new PhotoSessionsByPhotographDTO(photographer.getId(),
                photographer.getFirstName() + " " + photographer.getLastName(),
                photoSessionDTOS);
    }

}
