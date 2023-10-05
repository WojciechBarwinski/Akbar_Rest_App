package com.wojciech.barwinski.akbarrestapp;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolDTOPreview;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(false);

        // School -> DTO mapping
        modelMapper.createTypeMap(School.class, SchoolDTOPreview.class);

        // Phone -> DTO mapping
        modelMapper.createTypeMap(Phone.class, SchoolDTOPreview.class)
                .addMappings(mapper -> {
                    mapper.map(Phone::getNumber, SchoolDTOPreview::setPhone);
                });

        return modelMapper;
    }


}
