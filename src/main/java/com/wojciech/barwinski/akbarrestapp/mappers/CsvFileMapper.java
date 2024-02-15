package com.wojciech.barwinski.akbarrestapp.mappers;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wojciech.barwinski.akbarrestapp.csvCustomReder.SchoolCsvRepresentationDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.Reader;
import java.util.List;

@Slf4j
class CsvFileMapper {

    List<SchoolCsvRepresentationDTO> mapCsvToSchoolCsvRepresentations(Reader reader){
        log.debug("Map Csv file to SchoolCsvRepresentation");
            return new CsvToBeanBuilder<SchoolCsvRepresentationDTO>(reader)
                    .withType(SchoolCsvRepresentationDTO.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        }
    }

