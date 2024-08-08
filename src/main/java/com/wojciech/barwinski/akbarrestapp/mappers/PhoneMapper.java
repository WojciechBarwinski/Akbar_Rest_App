package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.PhoneToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
@RequiredArgsConstructor
public class PhoneMapper {

    private final ModelMapper modelMapper;


    Phone mapPhoneToUpdateToPhone(PhoneToUpdateDTO phone){
        return modelMapper.map(phone, Phone.class);
    }
}
