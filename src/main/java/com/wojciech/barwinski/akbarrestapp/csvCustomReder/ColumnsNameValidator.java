package com.wojciech.barwinski.akbarrestapp.csvCustomReder;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
class ColumnsNameValidator {

    String NAMES = "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";

    List<String> checkForMissingColumnNames(String columnNames) {
        log.debug("Check columns name from .csv");

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
