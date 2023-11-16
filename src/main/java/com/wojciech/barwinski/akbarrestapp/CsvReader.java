package com.wojciech.barwinski.akbarrestapp;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CsvReader {

    private static final String FILE_PATH = "src/main/resources/testSchools.csv";
    private final SchoolMapper schoolMapper;

    public CsvReader(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
    }

    public Set<School> parseCsvByMultipartFile(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return parseCsv(reader);
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }

    public Set<School> parseCsvByFilePath() {
        try {
            FileReader reader = new FileReader(FILE_PATH);
            return parseCsv(reader);
        } catch (FileNotFoundException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }

    private Set<School> parseCsv(Reader reader) {
        List<SchoolCsvRepresentation> parse = new CsvToBeanBuilder<SchoolCsvRepresentation>(reader)
                .withType(SchoolCsvRepresentation.class)
                .withSeparator(';')
                .build()
                .parse();

        return mapCsvToSchool(parse);
    }

    private Set<School> mapCsvToSchool(List<SchoolCsvRepresentation> csv) {
        return csv.stream()
                .map(schoolMapper::mapSchoolCsvRepToSchool)
                .collect(Collectors.toSet());
    }


}
