package com.wojciech.barwinski.akbarrestapp.mappers;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.entities.Phone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class SchoolRepresentationMapperHelper {

    static Phone mapPhone(String phone) {
        log.trace("     Mapping phone " + phone);

        if (phone == null || phone.trim().isEmpty()) {
            return new Phone.PhoneBuilder()
                    .phoneNote("brak numeru szkoły z bazy danych ministerstwa")
                    .build();
        }
        return new Phone.PhoneBuilder()
                .number(phone.trim())
                .owner("Główny numer szkoły")
                .build();
    }

    static Voivodeship mapVoivodeship(String voivodeship) {
        log.trace("     Mapping voivodeship " + voivodeship);

        if (voivodeship == null){
            log.warn("Voivodeship IS NULL!");
            return null;
        }

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
