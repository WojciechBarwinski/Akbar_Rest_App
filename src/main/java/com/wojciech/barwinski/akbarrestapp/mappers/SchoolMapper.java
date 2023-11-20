package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.AdditionalSchoolInformationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import com.wojciech.barwinski.akbarrestapp.entities.AdditionalSchoolInformation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    @Autowired
    ModelMapper modelMapper;


    public SchoolDTOPreview mapSchoolToSchoolDTOPreview(School school) {
        SchoolDTOPreview dto = modelMapper.map(school, SchoolDTOPreview.class);
        modelMapper.map(school.getAddress(), dto);
        if (!school.getPhones().isEmpty()) {
            modelMapper.map(school.getPhones().get(0), dto);
        }
        return dto;
    }

    public SchoolDTO mapSchoolToFullSchoolDTO(School school){
        SchoolDTO dto = modelMapper.map(school, SchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        dto.setAdditionalSchoolInformationDTO(mapInfoToInfoDTO(school.getAdditionalSchoolInformation()));
        return dto;
    }

    public School mapSchoolCsvRepToSchool(SchoolCsvRepresentation csv){
        School school = modelMapper.map(csv, School.class);
        Address address = modelMapper.map(csv, Address.class);
        school.addPhone(getPhoneFromCsv(csv.getPhone()));
        school.setAddress(address);
        return school;
    }


    private AdditionalSchoolInformationDTO mapInfoToInfoDTO(AdditionalSchoolInformation info){
        if (info == null){
            return new AdditionalSchoolInformationDTO();
        }
        AdditionalSchoolInformationDTO map = modelMapper.map(info, AdditionalSchoolInformationDTO.class);
        modelMapper.map(info.getNotation(), map);
        modelMapper.map(info.getSchedule(), map);
        modelMapper.map(info.getStatus(), map);

        return map;
    }

    private Phone getPhoneFromCsv(String phone){
        if (phone.isEmpty()){
            return new Phone.PhoneBuilder()
                    .phoneNote("brak numeru szkoły z bazy danych ministerstwa")
                    .build();
        }
        return new Phone.PhoneBuilder()
                .number(phone)
                .owner("Główny numer szkoły")
                .build();
    }
}
