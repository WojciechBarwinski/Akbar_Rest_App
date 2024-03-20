package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.PhoneToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import org.modelmapper.ModelMapper;

public class PhoneMapper {

    private final ModelMapper modelMapper;

    public PhoneMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    Phone mapPhoneToUpdateToPhone(PhoneToUpdateDTO phone){
        return modelMapper.map(phone, Phone.class);
    }
}
