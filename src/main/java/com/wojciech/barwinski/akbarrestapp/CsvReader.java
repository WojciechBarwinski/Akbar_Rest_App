package com.wojciech.barwinski.akbarrestapp;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvReader {
    private static final String FILE_PATH = "src/main/resources/test.csv";


    public Set<School> parseCsvByMultipartFile(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return parseCsv(reader);
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }

    public Set<School> parseCsvByFilePath() {
        try (Reader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return parseCsv(reader);
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }


    public Set<School> parseCsv(Reader reader) {
        HeaderColumnNameMappingStrategy<SchoolCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(SchoolCsvRepresentation.class);

        CsvToBean<SchoolCsvRepresentation> csvToBean = new CsvToBeanBuilder<SchoolCsvRepresentation>(reader)
                .withMappingStrategy(strategy)
                .withIgnoreEmptyLine(true)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return mapCsvToSchool(csvToBean);
    }

    private Set<School> mapCsvToSchool(CsvToBean<SchoolCsvRepresentation> csv) {
        return csv.stream()
                .map(csvLine -> School.builder()
                        .name(csvLine.getName())
                        //TODO rest
                        .build()
                )
                .collect(Collectors.toSet());
    }
}
