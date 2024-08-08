package com.wojciech.barwinski.akbarrestapp.staff.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SalesmanDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String note;
    //private List<SalesmanDeliveryDTO> trades = new ArrayList<>();
}
