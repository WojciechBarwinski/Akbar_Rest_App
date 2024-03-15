package com.wojciech.barwinski.akbarrestapp.mappers;


import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class MapperFacade {

    private static MapperFacade INSTANCE;
    private final SchoolToRosterMapper schoolToRosterMapper;
    private final SchoolToViewAndToUpdateMapper schoolToViewAndToUpdateMapper;
    private final SchoolRepresentationMapper csvSchoolMapper;

    private MapperFacade() {
        log.trace("MapperFacade create");
        ModelMapper modelMapper = new ModelMapper();
        schoolToRosterMapper = new SchoolToRosterMapper(modelMapper);
        schoolToViewAndToUpdateMapper = new SchoolToViewAndToUpdateMapper(modelMapper);
        csvSchoolMapper = new SchoolRepresentationMapper(modelMapper);
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

    public SchoolToViewDTO mapSchoolToSchoolToViewDTO(School school) {
        return schoolToViewAndToUpdateMapper.mapSchoolToSchoolToViewDTO(school);
    }

    public SchoolToUpdateDTO mapSchoolToSchoolToUpdateDTO(School school){
        return schoolToViewAndToUpdateMapper.mapSchoolToSchoolToUpdateDTO(school);
    }
    public SchoolToRosterDTO mapSchoolToSchoolToRosterDTO(School school) {
        return schoolToRosterMapper.mapSchoolToSchoolToRosterDTO(school);
    }

    public School mapSchoolRepresentationToSchool(SchoolRepresentation representation) {
        return csvSchoolMapper.mapSchoolRepresentationToSchool(representation);
    }

}
