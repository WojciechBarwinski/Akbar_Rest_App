package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortSchoolDTO {

    private Long rspo;
    private String name;
    private Voivodeship voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String phone;

/*    public ShortSchoolDTO(Long rspo, String name, Voivodeship voivodeship, String county, String borough, String city, String street, String phone) {
        this.rspo = rspo;
        this.name = name;
        this.voivodeship = voivodeship;
        this.county = county;
        this.borough = borough;
        this.city = city;
        this.street = street;
        this.phone = phone;
    }*/
}
