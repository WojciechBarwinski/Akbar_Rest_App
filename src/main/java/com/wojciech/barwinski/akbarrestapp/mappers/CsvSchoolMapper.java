package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;


public class CsvSchoolMapper {

    private final ModelMapper modelMapper;

    public CsvSchoolMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    School mapSchoolCsvRepresentationToSchool(SchoolCsvRepresentation csvRepresentation){

            School school = modelMapper.map(csvRepresentation, School.class);
            Address address = modelMapper.map(csvRepresentation, Address.class);
            address.setVoivodeship(getVoivodeshipFromString(csvRepresentation.getVoivodeship()));
            school.addPhone(getPhoneFromCsv(csvRepresentation.getPhone()));
            school.setAddress(address);

        return school;
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
