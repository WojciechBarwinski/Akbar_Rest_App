package com.wojciech.barwinski.akbarrestapp.staff.dtos;

import lombok.Data;

@Data
public class PhotographerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String note;

}
