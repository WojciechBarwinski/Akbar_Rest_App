package com.wojciech.barwinski.akbarrestapp.dtos;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneToViewDTO {

    private String number;
    private boolean isMain;
    private String owner;
    private String phoneNote;

}
