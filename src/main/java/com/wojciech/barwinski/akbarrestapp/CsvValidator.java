package com.wojciech.barwinski.akbarrestapp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class CsvValidator {

    private final String NAMES = "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";



    protected List<String> checkIfColumnsNamesAreCorrect(String columnNames) {

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


    private void validateSchoolDataFromCsv(List<SchoolCsvRepresentation> schoolList){

    }
}
