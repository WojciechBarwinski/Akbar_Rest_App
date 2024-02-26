package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class SchoolRepresentationMapperHelperTest {

    /*
     * These tests assume only dates that pass validation stage
     * */

    @Test
    void shouldCheckMappingPhoneWithValue() {

        String phoneNumberToTest = "123-45-67";
        String basePhoneOwner = "Główny numer szkoły";

        Phone phone = SchoolRepresentationMapperHelper.mapStringToPhone(phoneNumberToTest);

        Assertions.assertThat(phone.getNumber()).isEqualTo(phoneNumberToTest);
        Assertions.assertThat(phone.getOwner()).isEqualTo(basePhoneOwner);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    void shouldCheckMappingForEmptyStrings(String phoneNumberToTest) {

        String phoneNote = "brak numeru szkoły z bazy danych ministerstwa";

        Phone phone = SchoolRepresentationMapperHelper.mapStringToPhone(phoneNumberToTest);

        Assertions.assertThat(phone.getPhoneNote()).isEqualTo(phoneNote);
        Assertions.assertThat(phone.getOwner()).isEqualTo(null);
        Assertions.assertThat(phone.getNumber()).isEqualTo(null);
    }

    @Test
    void shouldCheckMappingForNull() {
        String phoneNumberToTest = null;
        String phoneNote = "brak numeru szkoły z bazy danych ministerstwa";

        Phone phone = SchoolRepresentationMapperHelper.mapStringToPhone(phoneNumberToTest);

        Assertions.assertThat(phone.getPhoneNote()).isEqualTo(phoneNote);
        Assertions.assertThat(phone.getOwner()).isEqualTo(null);
        Assertions.assertThat(phone.getNumber()).isEqualTo(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Lubelskie", "  Pomorskie  ", "Łódzkie."})
    void shouldCheckMappingCorrectVoivodeship(String voivodeshipToTest) {
        Voivodeship[] values = Voivodeship.values();

        Voivodeship voivodeship = SchoolRepresentationMapperHelper.mapStringToVoivodeship(voivodeshipToTest);

        Assertions.assertThat(voivodeship).isNotNull();
        Assertions.assertThat(voivodeship).isIn(values);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "Sieradz"})
    void shouldCheckMappingIncorrectVoivodeship(String voivodeshipToTest) {

        Voivodeship voivodeship = SchoolRepresentationMapperHelper.mapStringToVoivodeship(voivodeshipToTest);

        Assertions.assertThat(voivodeship).isNull();
    }


}