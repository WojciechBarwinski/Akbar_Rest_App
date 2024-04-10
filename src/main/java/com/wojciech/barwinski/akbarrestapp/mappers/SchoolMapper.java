package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Notation;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Schedule;
import com.wojciech.barwinski.akbarrestapp.entities.additionalSchoolInfo.Status;

class SchoolMapper {


    School mapSchoolToUpdateToSchool(SchoolToUpdateDTO schoolToUpdate) {
        return School.builder()
                .rspo(schoolToUpdate.getRspo())
                .type(schoolToUpdate.getType())
                .name(schoolToUpdate.getName())
                .email(schoolToUpdate.getEmail())
                .website(schoolToUpdate.getWebsite())
                .status(schoolToUpdate.getStatus())
                .address(mapAddress(schoolToUpdate))
                .additionalSchoolInformation(mapAdditionalSchoolInformation(schoolToUpdate.getAdditionalSchoolInformationDTO()))
                .build();
    }

    private Address mapAddress(SchoolToUpdateDTO schoolToUpdateDTO) {
        return Address.builder()
                .voivodeship(schoolToUpdateDTO.getVoivodeship())
                .county(schoolToUpdateDTO.getCounty())
                .borough(schoolToUpdateDTO.getBorough())
                .city(schoolToUpdateDTO.getCity())
                .street(schoolToUpdateDTO.getStreet())
                .zipCode(schoolToUpdateDTO.getZipCode())
                .addressNote(schoolToUpdateDTO.getAddressNote())
                .build();
    }

    private AdditionalSchoolInformation mapAdditionalSchoolInformation(AdditionalSchoolInformationDTO info) {
        Status status = Status.builder()
                .isOurs(info.getIsOurs())
                .isContracted(info.getIsContracted())
                .isPhoto(info.getIsPhoto())
                .isSettle(info.getIsSettle())
                .statusNote(info.getStatusNote())
                .build();

        Notation notation = Notation.builder()
                .notation1(info.getNotation1())
                .notation2(info.getNotation2())
                .notation3(info.getNotation3())
                .build();

        Schedule schedule = Schedule.builder()
                .contact(info.getContact())
                .signContractDate(info.getSignContractDate())
                .photographingDate(info.getPhotographingDate())
                .photographyDaysCount(info.getPhotographyDaysCount())
                .payoff(info.getPayoff())
                .other(info.getOther())
                .scheduleNote(info.getScheduleNote())
                .build();

        return AdditionalSchoolInformation.builder()
                .id(info.getId())
                .status(status)
                .notation(notation)
                .schedule(schedule)
                .build();
    }
}
