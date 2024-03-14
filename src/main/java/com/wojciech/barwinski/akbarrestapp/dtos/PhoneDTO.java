package com.wojciech.barwinski.akbarrestapp.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private Long id;
    private boolean isToRemove;
    private String phone;
    private boolean isMain;
    private String owner;
    private String phoneNote;
}
