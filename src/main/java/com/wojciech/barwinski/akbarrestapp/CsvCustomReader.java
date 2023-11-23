package com.wojciech.barwinski.akbarrestapp;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO dodac czas wczytywania pliku
@Component
public class CsvCustomReader {

    private static final String FILE_PATH = "src/main/resources/testSchools.csv";
    private final SchoolMapper schoolMapper;
    private final CsvValidator csvValidator;

    public CsvCustomReader(SchoolMapper schoolMapper, CsvValidator csvValidator) {
        this.schoolMapper = schoolMapper;
        this.csvValidator = csvValidator;
    }

    public Set<School> parseCsvByMultipartFile(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return parseCsv(reader);
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }

    public Set<School> parseCsvByFilePath() {
        try {BufferedReader readOneLine = new BufferedReader(new FileReader(FILE_PATH));
            List<String> missingNames = csvValidator.checkIfColumnsNamesAreCorrect(readOneLine.readLine());
                //TODO zmienic to aby kazdy plik mial własna validator i inaczej przekształcać dane tutaj
            if (missingNames.isEmpty()) {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                return parseCsv(reader);
            } else {
                throw new RuntimeException("Brakuje następujących nazw kolumn " + missingNames);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<School> parseCsv(BufferedReader reader) throws IOException {
        CsvToBean<SchoolCsvRepresentation> build = new CsvToBeanBuilder<SchoolCsvRepresentation>(reader)
                .withType(SchoolCsvRepresentation.class)
                .withSeparator(';')
                .build();

        //TODO metoda przyjmuje CsvToBean, validuje i zwraca listę tych obiektów które nadają sie do parsowania dalej.

        List<SchoolCsvRepresentation> parse = build.parse();
        return mapCsvToSchool(parse);
    }

    private Set<School> mapCsvToSchool(List<SchoolCsvRepresentation> csv) {
        return csv.stream()
                .map(schoolMapper::mapSchoolCsvRepToSchool)
                .collect(Collectors.toSet());
    }

}
