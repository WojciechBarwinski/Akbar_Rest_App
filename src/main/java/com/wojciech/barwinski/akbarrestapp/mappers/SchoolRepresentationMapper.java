package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.entities.Address;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import static com.wojciech.barwinski.akbarrestapp.mappers.SchoolRepresentationMapperHelper.*;

@Slf4j
public class SchoolRepresentationMapper {

    private final ModelMapper modelMapper;

    public SchoolRepresentationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    School mapSchoolRepresentationToSchool(SchoolRepresentation schoolRepresentation) {
        log.debug("Mapping CSV representation to School: " + schoolRepresentation.getName());

        School school = modelMapper.map(schoolRepresentation, School.class);
        Address address = modelMapper.map(schoolRepresentation, Address.class);

        Phone phone = mapPhone(schoolRepresentation.getPhone());
        Voivodeship voivodeship = mapVoivodeship(schoolRepresentation.getVoivodeship());

        school.addPhone(phone);
        address.setVoivodeship(voivodeship);
        school.setAddress(address);

        log.debug("Mapping completed");
        return school;
    }
}
