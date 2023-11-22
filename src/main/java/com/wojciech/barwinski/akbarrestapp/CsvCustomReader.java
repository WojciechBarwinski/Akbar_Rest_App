package com.wojciech.barwinski.akbarrestapp;


import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.SchoolMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
        try {
            BufferedReader readOneLine = new BufferedReader(new FileReader(FILE_PATH));

            if (checkIfColumnsNamesAreCorrect(readOneLine.readLine())){
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                return parseCsv(reader);
            }
            else {
                throw new RuntimeException("złe nazwy kolumn");
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


    private boolean checkIfColumnsNamesAreCorrect(String columnNames) throws IOException {
        String NAMES = "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Kod terytorialny ulica;Kod terytorialny miejscowosc;Kod terytorialny gmina;Telefon;Faks;E-mail;Strona www;Województwo;Powiat;Gmina;REGON;NIP;Publiczność status;Kategoria uczniów;Specyfika placówki;Imię i nazwisko dyrektora;Nazwa organu prowadzącego;Typ organu prowadzącego;Województwo organu prowadzącego;Powiat organu prowadzącego;Gmina organu prowadzącego;Data założenia;Data likwidacji;Miejsce w strukturze;Typ podmiotu nadrzędnego;RSPO podmiotu nadrzędnego;Nazwa podmiotu nadrzędnego;Liczba uczniów";

        String[] expectedNames = NAMES.split(";");
        String[] givenNames = columnNames.split(";");

        return Arrays.equals(expectedNames, givenNames);
    }

    /*
    * public Set<School> parseCsvByMultipartFile(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return parseCsv(reader);
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }

    public Set<School> parseCsvByFilePath() {
        try {BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            return parseCsv(reader);
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }*/
}
