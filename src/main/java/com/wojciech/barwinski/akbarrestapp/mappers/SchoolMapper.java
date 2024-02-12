package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolMapper {

    @Autowired
    ModelMapper modelMapper;


    public ShortSchoolDTO mapSchoolToSchoolDTOPreview(School school) {
        ShortSchoolDTO dto = modelMapper.map(school, ShortSchoolDTO.class);
        modelMapper.map(school.getAddress(), dto);
        if (!school.getPhones().isEmpty()) {
            modelMapper.map(school.getPhones().get(0), dto);
        }
        return dto;
    }



    public List<School> mapSchoolCsvRepToSchool(List<SchoolCsvRepresentation> csvList){
        List<School> schools = new ArrayList<>();

        for (SchoolCsvRepresentation csv : csvList) {
            School school = modelMapper.map(csv, School.class);
            Address address = modelMapper.map(csv, Address.class);
            address.setVoivodeship(getVoivodeshipFromString(csv.getVoivodeship()));
            school.addPhone(getPhoneFromCsv(csv.getPhone()));
            school.setAddress(address);

            schools.add(school);
        }

        return schools;
    }

    private Phone getPhoneFromCsv(String phone){
        if (phone == null || phone.isEmpty()){
            return new Phone.PhoneBuilder()
                    .phoneNote("brak numeru szkoły z bazy danych ministerstwa")
                    .build();
        }
        return new Phone.PhoneBuilder()
                .number(phone)
                .owner("Główny numer szkoły")
                .build();
    }

    private Voivodeship getVoivodeshipFromString(String voivodeship){
        for (Voivodeship v : Voivodeship.values()) {
            if (v.getName().equalsIgnoreCase(voivodeship)) {
                return v;
            }
        }
        return null;
    }
}
