package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SchoolRepresentationMapperHelperTest {

    /*
     * These tests assume only dates that pass validation stage
     * */

    @Test
    void shouldCheckMappingPhoneWithValue() {

        String phoneNumberToTest = "123-45-67";
        String basePhoneOwner = "Główny numer szkoły";

        Phone phone = SchoolRepresentationMapperHelper.mapStringToPhone(phoneNumberToTest);

        assertEquals(phone.getNumber(), phoneNumberToTest);
        assertEquals(phone.getOwner(), basePhoneOwner);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Lubelskie", "  Pomorskie  ", "Łódzkie."})
    void shouldCheckMappingCorrectVoivodeship(String voivodeshipToTest) {
        List<Voivodeship> voivodeshipsList = Arrays.asList(Voivodeship.values());

        Voivodeship voivodeship = SchoolRepresentationMapperHelper.mapStringToVoivodeship(voivodeshipToTest);

        assertNotEquals(voivodeship, null);
        assertTrue(voivodeshipsList.contains(voivodeship));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "Sieradz"})
    void shouldCheckMappingIncorrectVoivodeship(String voivodeshipToTest) {

        Voivodeship voivodeship = SchoolRepresentationMapperHelper.mapStringToVoivodeship(voivodeshipToTest);

        assertNull(voivodeship);
    }


}