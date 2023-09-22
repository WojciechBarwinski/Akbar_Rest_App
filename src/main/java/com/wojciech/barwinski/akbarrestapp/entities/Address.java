package com.wojciech.barwinski.akbarrestapp.entities;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String zipCode;

}
