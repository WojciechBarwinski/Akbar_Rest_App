package com.wojciech.barwinski.akbarrestapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortSchoolDTO {

    private Long rspo;
    private String name;
    private String voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String phone;
}
