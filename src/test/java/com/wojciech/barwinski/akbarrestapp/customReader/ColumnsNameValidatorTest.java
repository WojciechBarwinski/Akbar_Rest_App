package com.wojciech.barwinski.akbarrestapp.customReader;

import com.wojciech.barwinski.akbarrestapp.exception.InvalidCsvDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ColumnsNameValidatorTest {


    @ParameterizedTest
    @MethodSource("getCorrectColumnsNamesStrings")
    void shouldCheckCorrectColumnsNames(String columnsNames) {

        assertDoesNotThrow(() -> ColumnsNameValidator.validateColumnsName(columnsNames));
    }

    @ParameterizedTest
    @MethodSource("getIncorrectColumnsNamesStrings")
    void shouldCheckInCorrectColumnsNames(String columnsNames) {

        assertThrows(InvalidCsvDataException.class, () -> ColumnsNameValidator.validateColumnsName(columnsNames));
    }

    @Test
    void shouldCheckThatListOfMissingNamesIsCorrect(){
        String missingNames = "Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";
        String correctMissingAnswer = "Brakuje następujących nazw kolumn [numer rspo, miejscowość]";

        InvalidCsvDataException exception = assertThrows(InvalidCsvDataException.class, () -> ColumnsNameValidator.validateColumnsName(missingNames));

        assertEquals(correctMissingAnswer, exception.getMessage());
    }

    public static Stream<String> getCorrectColumnsNamesStrings() {

        String correctNames = "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";
        String moreThenNecessaryNames = "Numer RSPO;Typ;Dodatkowa nazwa;Nazwa;Ulica;Numer budynku;Numer lokalu;kolejna niepotrzebna nazwa;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";
        String namesWithDifferentLetterSize = "NUMER RSPO;Typ;nazwa;ulICA;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";
        return Stream.of(
                correctNames,
                moreThenNecessaryNames,
                namesWithDifferentLetterSize
        );
    }

    public static Stream<String> getIncorrectColumnsNamesStrings() {
        String empty = "";
        String wrongSeparator = "Numer RSPO:Typ:Nazwa:Ulica:Numer budynku:Numer lokalu:Kod pocztowy:Miejscowość:Telefon:E-mail:Strona www:Województwo:Powiat:Gmina:Publiczność status:";
        String missingNames = "Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;";

        return Stream.of(
                empty,
                wrongSeparator,
                missingNames
        );
    }
}