package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.csv.SchoolCsvRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class MapperFacade {

    private static MapperFacade INSTANCE;
    private final FullSchoolDTOMapper fullSchoolDTOMapper;
    private final ShortSchoolDTOMapper shortSchoolDTOMapper;
    private final CsvSchoolMapper csvSchoolMapper;

    private MapperFacade() {
        log.info("star - create MapperFacade");
        ModelMapper modelMapper = new ModelMapper();
        fullSchoolDTOMapper = new FullSchoolDTOMapper(modelMapper);
        shortSchoolDTOMapper = new ShortSchoolDTOMapper(modelMapper);
        csvSchoolMapper = new CsvSchoolMapper(modelMapper);
        log.info("finish - create MapperFacade");
    }

    public static MapperFacade getInstance() {
        log.info("using getInstance from MapperFacade");
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
        //Log tutaj czy w środku metody fullSchoolDTOMapper.mapSchoolToFullSchoolDTO?
        return fullSchoolDTOMapper.mapSchoolToFullSchoolDTO(school);
    }

    public ShortSchoolDTO mapSchoolToShortSchoolDTO(School school){
        return shortSchoolDTOMapper.mapSchoolToShortSchoolDTO(school);
    }

    public School mapSchoolCsvRepresentationToSchool(SchoolCsvRepresentation csvRepresentation){
        return csvSchoolMapper.mapSchoolCsvRepresentationToSchool(csvRepresentation);
    }
}
