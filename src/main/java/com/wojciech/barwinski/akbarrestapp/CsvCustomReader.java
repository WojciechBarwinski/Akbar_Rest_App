package com.wojciech.barwinski.akbarrestapp;


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

    public CsvCustomReader(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
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
            List<String> missingNames = checkIfColumnsNamesAreCorrect(readOneLine.readLine());

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

        List<SchoolCsvRepresentation> parse = new CsvToBeanBuilder<SchoolCsvRepresentation>(reader)
                .withType(SchoolCsvRepresentation.class)
                .withSeparator(';')
                .build().parse();

        return mapCsvToSchool(parse);
    }

    private Set<School> mapCsvToSchool(List<SchoolCsvRepresentation> csv) {
        return csv.stream()
                .map(schoolMapper::mapSchoolCsvRepToSchool)
                .collect(Collectors.toSet());
    }
    
    private List<String> checkIfColumnsNamesAreCorrect(String columnNames) {
        String NAMES = "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";

        String[] expectedColumnsName = NAMES.split(";");
        List<String> columnsNameToCheck = Arrays.asList(columnNames.split(";"));
        List<String> missingNames = new ArrayList<>();

        for (String name : expectedColumnsName) {
            if (!columnsNameToCheck.contains(name)) {
                missingNames.add(name);
            }
        }

        return missingNames;
    }
}
