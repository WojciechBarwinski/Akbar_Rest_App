package com.wojciech.barwinski.akbarrestapp;

public enum Voivodeship {
    DOLNOSLASKIE("Dolnośląskie"),
    KUJAWSKO_POMORSKIE("Kujawsko-Pomorskie"),
    LUBELSKIE("Lubelskie"),
    LUBUSKIE("Lubuskie"),
    MAZOWIECKIE("Mazowieckie"),
    MALOPOLSKIE("Małopolskie"),
    OPOLSKIE("Opolskie"),
    PODKARPACKIE("Podkarpackie"),
    PODLASKIE("Podlaskie"),
    POMORSKIE("Pomorskie"),
    SLASKIE("Śląskie"),
    SWIEOKRZYSKIE("Świętokrzyskie"),
    WARMINSKO_MAZURSKIE("Warmińsko-Mazurskie"),
    WIELKOPOLSKIE("Wielkopolskie"),
    ZACHODNIOPOMORSKIE("Zachodniopomorskie"),
    LODZKIE("Łódzkie");

    private final String name;

    Voivodeship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Voivodeship getVoivodeshipByString(String name) {
        for (Voivodeship value : Voivodeship.values()) {
            if (value.getName().equals(name.trim())) {
                return value;
            }
        }
        return null;
    }
}
