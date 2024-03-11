package com.wojciech.barwinski.akbarrestapp.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolSearchRequest {

    //Main Data
    private String type;
    private String schoolName;
    private String email;
    private String phoneNumber;

    //Address
    private String voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;

    //Status
    private Boolean isOurs;
    private Boolean isContracted;
    private Boolean isPhoto;
    private Boolean isSettle;

}
