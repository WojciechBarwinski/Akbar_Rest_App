package com.wojciech.barwinski.akbarrestapp.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneBuilderTest {


    @Test
    void shouldThrowExceptionForInvalidNumber() {
        //given
        String invalidNumber = "123ad";
        String expectedMessage = "Number must be 9 digits";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Phone.PhoneBuilder().number(invalidNumber),
                "it should throw exception but it not");

        //then
        assertTrue(exception.getMessage()
                .contains(expectedMessage));
    }

}