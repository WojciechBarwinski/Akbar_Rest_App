package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SchoolDTO {

    private Long rspo;
    private String name;
    private Voivodeship voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String phone;
}
