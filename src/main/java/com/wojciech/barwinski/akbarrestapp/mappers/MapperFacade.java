package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.modelmapper.ModelMapper;


public class MapperFacade {

    private static MapperFacade INSTANCE;
    private final MapperFullSchoolDTO mapperFullSchoolDTO;
    private final MapperShortSchoolDTO mapperShortSchoolDTO;
    private final MapperSchoolFromCsv mapperSchoolFromCsv;

    private MapperFacade() {
        ModelMapper modelMapper = new ModelMapper();
        mapperFullSchoolDTO = new MapperFullSchoolDTO(modelMapper);
        mapperShortSchoolDTO = new MapperShortSchoolDTO(modelMapper);
        mapperSchoolFromCsv = new MapperSchoolFromCsv(modelMapper);
    }

    public static MapperFacade getInstance() {
        if (INSTANCE == null) {
            synchronized (MapperFacade.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MapperFacade();
                }
            }
        }
        return INSTANCE;
    }


    public FullSchoolDTO mapSchoolToFullSchoolDTO(School school){
        return mapperFullSchoolDTO.mapSchoolToFullSchoolDTO(school);
    }

    public ShortSchoolDTO mapSchoolToShortSchoolDTO(School school){
        return mapperShortSchoolDTO.mapSchoolToShortSchoolDTO(school);
    }

    public School mapSchoolCsvRepresentationToSchool(SchoolCsvRepresentation csvRepresentation){
        return mapperSchoolFromCsv.mapSchoolCsvRepresentationToSchool(csvRepresentation);
    }
}
