package com.wojciech.barwinski.akbarrestapp.dtos;

import lombok.Data;

@Data
public class SchoolSearchRequest {

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
    private boolean isOurs;
    private boolean isContracted;
    private boolean isPhoto;
    private boolean isSettle;

}
