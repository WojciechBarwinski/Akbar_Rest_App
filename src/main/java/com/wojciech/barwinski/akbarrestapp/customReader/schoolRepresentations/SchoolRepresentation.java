package com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolRepresentation {

    private String rspo;
    private String type;
    private String name;
    private String street;
    private String buildingNumber;
    private String localNumber;
    private String zipCode;
    private String city;
    private String phone;
    private String email;
    private String website;
    private String voivodeship;
    private String county;
    private String borough;
    private String status;
}
