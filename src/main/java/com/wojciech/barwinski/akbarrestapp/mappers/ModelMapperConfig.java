package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
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

        modelMapper.createTypeMap(Phone.class, SchoolToRosterDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Phone::getNumber, SchoolToRosterDTO::setPhone);
                });

        return modelMapper;
    }
}
