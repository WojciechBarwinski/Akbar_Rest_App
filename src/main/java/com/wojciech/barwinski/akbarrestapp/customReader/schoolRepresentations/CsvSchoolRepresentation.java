package com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations;

import com.opencsv.bean.CsvBindByName;


public class CsvSchoolRepresentation extends SchoolRepresentation {

    @CsvBindByName(column = "Numer RSPO")
    private String rspo;

    @CsvBindByName(column = "Typ")
    private String type;

    @CsvBindByName(column = "Nazwa")
    private String name;

    @CsvBindByName(column = "Ulica")
    private String street;

    @CsvBindByName(column = "Numer budynku")
    private String buildingNumber;

    @CsvBindByName(column = "Numer lokalu")
    private String localNumber;

    @CsvBindByName(column = "Kod pocztowy")
    private String zipCode;

    @CsvBindByName(column = "Miejscowość")
    private String city;

    @CsvBindByName(column = "Telefon")
    private String phone;

    @CsvBindByName(column = "E-mail")
    private String email;

    @CsvBindByName(column = "Strona www")
    private String website;

    @CsvBindByName(column = "Województwo")
    private String voivodeship;

    @CsvBindByName(column = "Powiat")
    private String county;

    @CsvBindByName(column = "Gmina")
    private String borough;

    @CsvBindByName(column = "Publiczność status")
    private String status;
}
