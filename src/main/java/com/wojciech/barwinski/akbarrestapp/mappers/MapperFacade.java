package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.csvCustomReder.SchoolCsvRepresentationDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.io.Reader;
import java.util.List;

@Slf4j
public class MapperFacade {

    private static MapperFacade INSTANCE;
    private final FullSchoolDTOMapper fullSchoolDTOMapper;
    private final ShortSchoolDTOMapper shortSchoolDTOMapper;
    private final CsvSchoolMapper csvSchoolMapper;
    private final CsvFileMapper csvFileMapper;

    private MapperFacade() {
        log.trace("MapperFacade create");
        ModelMapper modelMapper = new ModelMapper();
        fullSchoolDTOMapper = new FullSchoolDTOMapper(modelMapper);
        shortSchoolDTOMapper = new ShortSchoolDTOMapper(modelMapper);
        csvSchoolMapper = new CsvSchoolMapper(modelMapper);
        csvFileMapper = new CsvFileMapper();
    }

    public static MapperFacade getInstance() {
        log.trace("using getInstance from MapperFacade");
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
        return fullSchoolDTOMapper.mapSchoolToFullSchoolDTO(school);
    }

    public ShortSchoolDTO mapSchoolToShortSchoolDTO(School school){
        return shortSchoolDTOMapper.mapSchoolToShortSchoolDTO(school);
    }

    public School mapSchoolCsvRepresentationToSchool(SchoolCsvRepresentationDTO csvRepresentation){
        return csvSchoolMapper.mapSchoolCsvRepresentationToSchool(csvRepresentation);
    }

    public List<SchoolCsvRepresentationDTO> mapCsvToSchoolCsvRepresentations(Reader reader){
        return csvFileMapper.mapCsvToSchoolCsvRepresentations(reader);
    }
}
