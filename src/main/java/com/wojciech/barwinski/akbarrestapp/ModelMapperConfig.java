package com.wojciech.barwinski.akbarrestapp;

import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(false);

        modelMapper.createTypeMap(Phone.class, ShortSchoolDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Phone::getNumber, ShortSchoolDTO::setPhone);
                });

        return modelMapper;
    }
}
