package com.wojciech.barwinski.akbarrestapp.entities;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.Audited;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@Audited
public class Address {

    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;

    @Column(length = 30)
    @Size(max = 30)
    private String county;

    @Column(length = 30)
    @Size(max = 30)
    private String borough;

    @Column(length = 15)
    @Size(max = 15)
    private String city;

    @Column(length = 50)
    @Size(max = 50)
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

}
