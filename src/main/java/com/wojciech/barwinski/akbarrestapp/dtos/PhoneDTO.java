package com.wojciech.barwinski.akbarrestapp.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private String phone;
    private boolean isMain;
    private String owner;
    private String phoneNote;
}
