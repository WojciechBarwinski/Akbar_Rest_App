package com.wojciech.barwinski.akbarrestapp.customReader;


import com.wojciech.barwinski.akbarrestapp.exception.InvalidCsvDataException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
class ColumnsNameValidator {

    private static final String NAMES = "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";

    static void validateColumnsName(String columnNames) {
        log.debug("Check columns name from .csv");
        System.out.println("sprawdzanie nazw kolumn");
        List<String> expectedColumnsName = Arrays.asList(NAMES.toLowerCase().split(";"));
        List<String> columnsNameToCheckSet = Arrays.asList(columnNames.toLowerCase().split(";"));
        List<String> missingNames = new ArrayList<>(expectedColumnsName);

        missingNames.removeAll(columnsNameToCheckSet);
        checkMissingNames(missingNames);
    }

    private static void checkMissingNames(List<String> missingNames) {
        if (!missingNames.isEmpty()) {
            InvalidCsvDataException wrongColumnsNameException = new InvalidCsvDataException("Brakuje następujących nazw kolumn " + missingNames);
            log.warn(wrongColumnsNameException.getMessage(), wrongColumnsNameException);
            throw wrongColumnsNameException;
        }
    }

}
