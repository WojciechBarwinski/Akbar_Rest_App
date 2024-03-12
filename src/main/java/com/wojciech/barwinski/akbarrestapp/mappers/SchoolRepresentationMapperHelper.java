package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class SchoolRepresentationMapperHelper {

    static Phone mapStringToPhone(String phone) {
        log.trace("     Mapping phone " + phone);

        return Phone.builder()
                .number(phone.trim())
                .isMain(true)
                .owner("Główny numer szkoły")
                .build();
    }

    static Voivodeship mapStringToVoivodeship(String voivodeship) {
        log.trace("     Mapping voivodeship " + voivodeship);

        String voivodeshipAfterTrimAndReplace = voivodeship.trim().replaceAll("[.,;:/+`]", "");
        Voivodeship correctVoivodeship = null;

        for (Voivodeship v : Voivodeship.values()) {
            if (v.getName().equalsIgnoreCase(voivodeshipAfterTrimAndReplace)) {
                correctVoivodeship = v;
            }
        }
        log.warn("voivodeship after mapis -> " + correctVoivodeship);
        return correctVoivodeship;
    }

}
