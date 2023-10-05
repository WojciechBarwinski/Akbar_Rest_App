package com.wojciech.barwinski.akbarrestapp.entities;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
/*@Setter*/
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;

    @Column(length = 30)
    @Size(max = 30)
    private String county;

    @Column(length = 10)
    @Size(max = 10)
    private String borough;

    @Column(length = 15)
    @Size(max = 15)
    private String city;

    @Column(length = 40)
    @Size(max = 40)
    private String street;

    @Column(length = 6)
    @Size(max = 6)
    private String zipCode;

    @Column(length = 500)
    @Size(max = 500)
    private String addressNote;

    public Address(Voivodeship voivodeship, String county, String borough, String city, String street, String zipCode) {
        this.voivodeship = voivodeship;
        this.county = county;
        this.borough = borough;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    /*
    @Column and @Size is from the longest city/street/county etc
     */

}
