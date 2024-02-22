package com.wojciech.barwinski.akbarrestapp.customReader;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.CsvSchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import lombok.extern.slf4j.Slf4j;

import java.io.Reader;
import java.util.List;

@Slf4j
class CsvFileMapper {

    static List<SchoolRepresentation> mapCsvToSchoolRepresentations(Reader reader) {
        log.debug("Map Csv file to SchoolRepresentation");
        return new CsvToBeanBuilder<SchoolRepresentation>(reader)
                .withType(CsvSchoolRepresentation.class)
                .withSeparator(';')
                .build()
                .parse();
    }

    /*    static List<CsvSchoolRepresentation> mapCsvToSchoolCsvRepresentations(Reader reader) {
        log.debug("Map Csv file to SchoolCsvRepresentation");
        return new CsvToBeanBuilder<CsvSchoolRepresentation>(reader)
                .withType(CsvSchoolRepresentation.class)
                .withSeparator(';')
                .build()
                .parse();
    }*/
}

